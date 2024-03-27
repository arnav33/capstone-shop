package com.capstone.usermanagementservice.dtos;

import com.capstone.usermanagementservice.entity.Address;
import com.capstone.usermanagementservice.entity.CustomGrantedAuthority;
import com.capstone.usermanagementservice.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UUID id;
    private String username;
    private String email;
    private String mobile;
    private String firstName;
    private String lastName;
    private List<CustomGrantedAuthority> authorities;
    private String token;
    private Address address;

    public LoginResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.authorities = user.getAuthorities();
        this.address = user.getAddress();
    }
}
