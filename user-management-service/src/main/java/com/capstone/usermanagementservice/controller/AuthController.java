package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) throws UserAlreadyExistsException {
        return this.authService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return this.authService.login(user);
    }
}
