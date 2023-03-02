package br.com.ecopoints.userservice.services;


import br.com.ecopoints.userservice.db.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String tokenExpiration;

    @Value("${jwt.secret}")
    private String tokenSecret;

    public String generateToken(Authentication authentication) {
        User user = (User)  authentication.getPrincipal();
        Date dateNow = new Date();
        Date dateExpiration = new Date(dateNow.getTime() + Long.parseLong(tokenExpiration));
        return Jwts.builder()
                .setIssuer("Ecopoints")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256, tokenSecret)
                .compact();
    }
}
