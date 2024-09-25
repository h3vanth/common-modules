package io.formulate.common.auth.web.filter;

import io.formulate.common.auth.web.model.JwtClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public abstract class AbstractAuthorizationFilter extends OncePerRequestFilter {
    protected abstract Collection<String> getRoutesToBeSkipped();

    protected abstract JwtClaims verifyTokenAndGetClaims(HttpServletRequest request);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        JwtClaims claims = verifyTokenAndGetClaims(request);

        Authentication authentication = new UsernamePasswordAuthenticationToken(claims.getUserId(), null, claims.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return getRoutesToBeSkipped().contains(request.getRequestURI());
    }
}
