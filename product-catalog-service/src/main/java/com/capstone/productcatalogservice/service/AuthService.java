package com.capstone.productcatalogservice.service;

import javax.naming.AuthenticationException;
import java.net.URISyntaxException;

public interface AuthService {

    String validate(String token) throws URISyntaxException, AuthenticationException;
}
