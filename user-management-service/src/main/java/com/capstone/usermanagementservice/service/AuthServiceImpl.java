package com.capstone.usermanagementservice.service;

import com.capstone.usermanagementservice.entity.Session;
import com.capstone.usermanagementservice.entity.User;
import com.capstone.usermanagementservice.enumerations.SessionStatus;
import com.capstone.usermanagementservice.exception.UserAlreadyExistsException;
import com.capstone.usermanagementservice.repository.SessionRepository;
import com.capstone.usermanagementservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.ws.rs.core.HttpHeaders;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    SessionRepository sessionRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.sessionRepository = sessionRepository;
    }

    @Override
    public String register(User user) throws UserAlreadyExistsException {
        Optional<User> userOptional = this.userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User Already Exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        this.userRepository.save(user);
        return "User added to db";
    }

    @Override
    public User login(User userRequest) {
        Optional<User> optionalUser = this.userRepository.findByUsername(userRequest.getUsername());
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password entered");
        }
        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("email", user.getEmail());
        jsonMap.put("roles", List.of(user.getAuthorities()));
        jsonMap.put("createdAt", new Date());
        jsonMap.put("expiryAt", DateUtils.addDays(new Date(), 30));

        String jws = Jwts.builder()
                .claims(jsonMap)
                .signWith(key, alg)
                .compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken(jws);
        session.setUser(user);
        //session.setExpiringAt(//current time + 30 days);
        sessionRepository.save(session);

        User userDto = new User();
        userDto.setEmail(userRequest.getEmail());

        MultiValueMapAdapter<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + jws);

        return user;
    }
}
