package com.capstone.usermanagementservice.controller;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Object getUserByUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.userService.addUser(user);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(@RequestBody User user) {
        this.userService.deleteUser(user.getUsername());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public String updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
        return "User updated";
    }
}
