package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    public String register(User user) throws UserAlreadyExistsException;
    public User login(User user);
}
