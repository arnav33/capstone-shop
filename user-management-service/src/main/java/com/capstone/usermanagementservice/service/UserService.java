package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Object getUserByUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void updateUser(User user) {
        Optional<User> userOptional = this.userRepository.findByUsername(user.getUsername());
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User doesn't exist.");
        }
        User savedUser = userOptional.get();
        savedUser.setUsername(user.getUsername());
        savedUser.setEmail(user.getEmail());
        savedUser.setMobile(user.getMobile());
        this.userRepository.save(savedUser);
    }
}
