package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.dtos.*;
import com.capstone.usermanagementservice.enumerations.SessionStatus;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        return this.authService.register(registrationRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return this.authService.login(loginRequest);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return authService.validate(authorization.split(" ")[1]).toString();
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        this.authService.logout(authorization.split(" ")[1]);
    }
}
