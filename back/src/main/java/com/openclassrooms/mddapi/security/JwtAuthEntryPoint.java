package com.openclassrooms.mddapi.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Entry point for unauthorized requests.
 * Returns a 401 HTTP response when authentication is required but not provided or invalid.
 */
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    /**
     * Rejects unauthenticated requests with a 401 Unauthorized error.
     *
     * @param request the incoming HTTP request
     * @param response the HTTP response
     * @param authException the exception that triggered this entry point
     * @throws IOException if an I/O error occurs while writing the response
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "401 Unauthorized");

    }
}