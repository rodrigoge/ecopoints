package br.com.ecopoints.userservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class ValidationFilter extends BasicAuthenticationFilter {
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String tokenSecret;

    public ValidationFilter(AuthenticationManager authenticationManager,
                            String tokenSecret) {
        super(authenticationManager);
        this.tokenSecret = tokenSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        var headerAttribute = request.getHeader(HEADER);
        if (headerAttribute == null) {
            chain.doFilter(request, response);
            return;
        }
        if (!headerAttribute.startsWith(PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        var token = headerAttribute.replace(PREFIX, "");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        var user = JWT
                .require(Algorithm.HMAC256(tokenSecret))
                .build()
                .verify(token)
                .getSubject();
        if (user == null) return null;
        return new UsernamePasswordAuthenticationToken(user, null, null);
    }
}
