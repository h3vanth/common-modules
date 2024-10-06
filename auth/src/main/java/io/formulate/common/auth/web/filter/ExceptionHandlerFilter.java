package io.formulate.common.auth.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.formulate.common.auth.web.constant.AuthConstants;
import io.formulate.common.ws.error.AppError;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            enhanceResponse(exception, response);
        }
    }

    protected void enhanceResponse(Exception exception, HttpServletResponse response) throws IOException {
        response.setHeader(AuthConstants.Header.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        int statusCode;
        AppError error;

        switch (exception.getClass().getSimpleName()) {
            case "BadCredentialsException":
                statusCode = HttpServletResponse.SC_UNAUTHORIZED;
                error = new AppError(exception.getMessage());
                break;
            default:
                statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                error = new AppError("Something went wrong!");
        }

        response.setStatus(statusCode);
        response.getWriter().write(objectMapper.writeValueAsString(error));
        response.getWriter().flush();
    }
}
