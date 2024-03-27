package com.capstone.paymentservice.security.services;

import javax.naming.AuthenticationException;
import java.net.URISyntaxException;

public interface AuthService {

    String validate(String token) throws URISyntaxException, AuthenticationException;
}
