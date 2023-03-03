package br.com.ecopoints.userservice.filters;

import br.com.ecopoints.userservice.db.User;
import br.com.ecopoints.userservice.exceptions.ErrorTypeEnum;
import br.com.ecopoints.userservice.exceptions.FlowException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private String tokenExpiration;
    private String tokenSecret;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            var user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    ));
        } catch (IOException e) {
            throw new FlowException(
                    HttpStatus.BAD_REQUEST,
                    ErrorTypeEnum.INTERNAL_SERVER,
                    "Authentication failed."
            );
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        var user = (User) authResult.getPrincipal();
        var token = JWT
                .create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpiration))
                .sign(Algorithm.HMAC256(tokenSecret));
        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
