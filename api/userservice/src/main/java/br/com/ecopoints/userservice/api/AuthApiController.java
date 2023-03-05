package br.com.ecopoints.userservice.api;

import br.com.ecopoints.userservice.dto.LoginTO;
import br.com.ecopoints.userservice.dto.TokenTO;
import br.com.ecopoints.userservice.services.TokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/login")
@Api(tags = "Authentication endpoint")
public class AuthApiController {

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenTO> login(@RequestBody LoginTO loginTO) throws ParseException {
        var token = tokenService.generateToken(loginTO);
        return ResponseEntity.ok(token);
    }
}
