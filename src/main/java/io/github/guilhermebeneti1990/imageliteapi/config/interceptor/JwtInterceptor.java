package io.github.guilhermebeneti1990.imageliteapi.config.interceptor;

import io.github.guilhermebeneti1990.imageliteapi.application.jwt.InvalidTokenException;
import io.github.guilhermebeneti1990.imageliteapi.application.jwt.JwtService;
import io.github.guilhermebeneti1990.imageliteapi.domain.entities.User;
import io.github.guilhermebeneti1990.imageliteapi.domain.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtInterceptor extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);

        if(token != null) {
            try {
                String email = jwtService.getEmailFromToken(token);
                User user = userService.getByEmail(email);
                setUserAsAuthenticated(user);
            } catch (InvalidTokenException e) {
                log.error("Invalid Token: {}", e.getMessage());
            } catch (Exception e) {
                log.error("Validation Token Error: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private void setUserAsAuthenticated(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null) {
            String[] authHeadersParts = authHeader.split(" ");
            if(authHeadersParts.length == 2) {
                return authHeadersParts[1];
            }
        }
        return null;
    }
}
