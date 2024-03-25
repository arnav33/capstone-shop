package com.capstone.usermanagementservice.repository;

import com.capstone.usermanagementservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
