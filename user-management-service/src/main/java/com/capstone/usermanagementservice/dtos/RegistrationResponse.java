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
public class RegistrationResponse {
    private UUID id;
    private String username;
    private String email;
    private String mobile;
    private String firstName;
    private String lastName;
    private Address address;
    private List<CustomGrantedAuthority> authorities;

    public RegistrationResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.authorities = user.getAuthorities();
    }
}
