package br.com.ecopoints.userservice.services;


import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.dto.LoginTO;
import br.com.ecopoints.userservice.dto.TokenTO;
import br.com.ecopoints.userservice.exceptions.ErrorTypeEnum;
import br.com.ecopoints.userservice.exceptions.FlowException;
import br.com.ecopoints.userservice.mappers.UserMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@Log4j2
public class TokenService {

    @Value("${jwt.expiration}")
    private int tokenExpiration;

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public TokenTO generateToken(LoginTO loginTO) throws ParseException {
        log.info("Starting generate token flow");
        if (loginTO == null || loginTO.getEmail() == null || loginTO.getPassword() == null) return null;
        var email = loginTO.getEmail();
        var password = loginTO.getPassword();
        var user = (User) authenticationService.loadUserByUsername(email);
        log.info("Verifying matches user password");
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new FlowException(
                    HttpStatus.BAD_REQUEST,
                    ErrorTypeEnum.INVALID_REQUEST,
                    "E-mail or password invalidates");
        log.info("Create JWT token");
        var dateExpiration = new Date(System.currentTimeMillis() + tokenExpiration);
        var token = JWT.create()
                .withSubject(email)
                .withExpiresAt(dateExpiration)
                .sign(Algorithm.HMAC256(tokenSecret));
        log.info("Building tokenTO object");
        var tokenTO = TokenTO
                .builder()
                .token(token)
                .tokenExpiration(dateExpiration)
                .user(userMapper.fromUser(user))
                .build();
        log.info("TokenTO object built");
        return tokenTO;
    }
}
