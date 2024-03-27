package com.capstone.usermanagementservice.dtos;

import com.capstone.usermanagementservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String mobile;
    private Address address;
}
