package com.capstone.usermanagementservice.constant;

public class Constants {

    public static final String[] WHITELISTED_GET_ENDPOINTS = {
            "/unsecured",
            "/auth",
            "/api/user"
    };

    public static final String[] WHITELISTED_POST_ENDPOINTS = {
            "/auth/register",
            "/auth/login",
            "/auth/validate",
            "/auth/logout"
    };

    public static final String[] WHITELISTED_PUT_ENDPOINTS = {
            "/api/user"
    };
}
