package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Object getUserByUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
        return "User updated";
    }
}
