package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.dto.LoginTO;
import br.com.ecopoints.userservice.dto.TokenTO;
import br.com.ecopoints.userservice.services.TokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "Authentication endpoint")
public class AuthApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenTO> auth(@RequestBody @Validated LoginTO loginTO) {
        UsernamePasswordAuthenticationToken usrPwdAuthToken = new UsernamePasswordAuthenticationToken(
                loginTO.getEmail(),
                loginTO.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(usrPwdAuthToken);
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(TokenTO.builder().type("Bearer").token(token).build());
    }
}
