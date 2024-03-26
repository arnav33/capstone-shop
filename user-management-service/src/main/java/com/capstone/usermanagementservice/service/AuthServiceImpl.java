package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.dtos.LoginRequest;
import com.capstone.usermanagementservice.dtos.LoginResponse;
import com.capstone.usermanagementservice.dtos.RegistrationRequest;
import com.capstone.usermanagementservice.dtos.RegistrationResponse;
import com.capstone.usermanagementservice.entity.Session;
import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.enumerations.SessionStatus;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.repository.SessionRepository;
import com.capstone.usermanagementservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    SessionRepository sessionRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.sessionRepository = sessionRepository;
    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) throws UserAlreadyExistsException {
        Optional<User> userOptional = this.userRepository.findByUsername(registrationRequest.getUsername());
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists.");
        }
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        this.userRepository.save(user);
        return new RegistrationResponse("User added to db.");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> optionalUser = this.userRepository.findByUsername(loginRequest.getUsername());
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password entered");
        }
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();
        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("email", user.getEmail());
//        jsonMap.put("authorities", user.getAuthorities());
        jsonMap.put("createdAt", new Date());
        jsonMap.put("expiryAt", DateUtils.addDays(new Date(), 30));

        String jws = Jwts.builder()
                .claim("createdAt", new Date())
                .claim("expiryAt", DateUtils.addDays(new Date(), 30))
                .signWith(key, alg)
                .compact();

        Session session = new Session();
//        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(jws);
        session.setUser(user);
        session.setExpiringAt((Date) jsonMap.get("expiryAt"));
        session.setSessionStatus(SessionStatus.ACTIVE);
        //session.setExpiringAt(//current time + 30 days);
        sessionRepository.save(session);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.getEmail());
        loginResponse.setMobile(user.getMobile());
        loginResponse.setUsername(user.getUsername());
        loginResponse.setToken(jws);

        return loginResponse;
    }

    public SessionStatus validate(String token) {
        Optional<Session> sessionOptional = sessionRepository.findByToken(token);

        if (sessionOptional.isEmpty()) {
            return null;
        }

        Session session = sessionOptional.get();

        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }

        Date currentTime = new Date();
        if (session.getExpiringAt().before(currentTime)) {
            return SessionStatus.ENDED;
        }

        if(!session.getToken().equals(token)) {
            return null;
        }

        return SessionStatus.ACTIVE;
    }

    public void logout(String token) {
        Optional<Session> sessionOptional = sessionRepository.findByToken(token);
        if (sessionOptional.isEmpty()) {
            return;
        }
        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
    }
}
