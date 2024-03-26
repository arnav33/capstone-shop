package com.capstone.usermanagementservice.repository;

import com.capstone.usermanagementservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Optional<Session> findByTokenAndUser_Id(String token, UUID userId);
}
