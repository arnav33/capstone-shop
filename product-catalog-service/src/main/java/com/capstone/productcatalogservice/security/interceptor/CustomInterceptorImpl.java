package com.capstone.productcatalogservice.security.interceptor;

import com.capstone.productcatalogservice.security.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;

public class CustomInterceptorImpl implements HandlerInterceptor {

    AuthService authService;

    public CustomInterceptorImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null) {
            throw new AuthenticationException("Access Denied!");
        }
        String status = this.authService.validate(authorizationHeader);
        if(!status.equals("ACTIVE")) {
            throw new AuthenticationException("Access denied");
        }
        return true;
    }
}
