package io.formulate.common.auth.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.formulate.common.auth.web.constant.AuthConstants;
import io.formulate.common.ws.error.AppError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Base64;

public class BaseAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public BaseAuthenticationFilter(String authPath, AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.setFilterProcessesUrl(authPath);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // Handle exception
        String[] credentials = new String(
                Base64.getDecoder().decode(request.getHeader(AuthConstants.Header.AUTHORIZATION).split(" ")[1]))
                .split(":");
        String email = credentials[0];
        String password = credentials[1];

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        return authenticationManager.authenticate(authentication);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(new AppError(failed.getMessage())));
        response.getWriter().flush();
    }
}
