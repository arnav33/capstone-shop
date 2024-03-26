package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.dtos.LoginRequest;
import com.capstone.usermanagementservice.dtos.LoginResponse;
import com.capstone.usermanagementservice.dtos.RegistrationRequest;
import com.capstone.usermanagementservice.dtos.RegistrationResponse;
import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.enumerations.SessionStatus;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AuthService {

    public RegistrationResponse register(RegistrationRequest registrationRequest) throws UserAlreadyExistsException;
    public LoginResponse login(LoginRequest loginRequest);
    SessionStatus validate(String token, UUID userId);
    ResponseEntity<Void> logout(String token, UUID userId);
}
