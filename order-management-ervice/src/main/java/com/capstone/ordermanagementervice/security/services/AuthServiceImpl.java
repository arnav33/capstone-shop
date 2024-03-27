package com.capstone.ordermanagementervice.security.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    @Value("${auth.server}")
    private String authServer;

    public AuthServiceImpl() {
        this.restTemplate = new RestTemplate();
    }
    @Override
    public String validate(String token) throws AuthenticationException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result = restTemplate.exchange(authServer, HttpMethod.GET, entity, String.class);
        if(result.getBody() == null || !result.getBody().toString().equals("ACTIVE")) {
            throw new AuthenticationException("Access Denied");
        }
        return result.getBody().toString();
    }
}
