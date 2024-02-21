package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.service.AuthService;
import com.capstone.usermanagementservice.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @GetMapping
    public String test() {
        return "hello";
    }
    @PostMapping
    public String register(@RequestBody @Validated User user) throws UserAlreadyExistsException {
        return this.authService.register(user);
    }
}
