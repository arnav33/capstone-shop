package com.capstone.usermanagementservice.dtos;

import com.capstone.usermanagementservice.entity.CustomGrantedAuthority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String username;
    private String email;
    private String mobile;
    private List<CustomGrantedAuthority> authorities;
    private String token;
}
