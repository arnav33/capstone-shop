package com.capstone.productcatalogservice.interceptor;

import com.capstone.productcatalogservice.service.AuthService;
import com.capstone.productcatalogservice.service.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import java.lang.annotation.Annotation;

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
