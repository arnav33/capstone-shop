package com.capstone.usermanagementservice.entity;

import com.capstone.usermanagementservice.enumerations.SessionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity {
    private String token;
    private String expiringAt;
    @ManyToOne
    private User user;
    @Enumerated
    private SessionStatus sessionStatus;
}
