package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.dtos.LoginRequest;
import com.capstone.usermanagementservice.dtos.LoginResponse;
import com.capstone.usermanagementservice.dtos.RegistrationRequest;
import com.capstone.usermanagementservice.dtos.RegistrationResponse;
import com.capstone.usermanagementservice.enumerations.SessionStatus;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    RegistrationResponse register(RegistrationRequest registrationRequest) throws UserAlreadyExistsException;
    LoginResponse login(LoginRequest loginRequest);
    SessionStatus validate(String token);
    void logout(String token);
}
