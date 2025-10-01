package com.example.obo.config;

import com.example.obo.service.OBOService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@Configuration
@Order(1)
public class SecurityConfig implements Filter {

    private final OBOService oboService;

    public SecurityConfig(OBOService oboService) {
        this.oboService = oboService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if (req.getRequestURI().startsWith("/api")) {
            String authHeader = req.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
                return;
            }
            String token = authHeader.substring(7);
            if (!oboService.validateOboToken(token)) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid OBO token");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
