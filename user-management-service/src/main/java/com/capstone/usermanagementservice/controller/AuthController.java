package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.dtos.LoginRequest;
import com.capstone.usermanagementservice.dtos.LoginResponse;
import com.capstone.usermanagementservice.dtos.RegistrationRequest;
import com.capstone.usermanagementservice.dtos.RegistrationResponse;
import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
