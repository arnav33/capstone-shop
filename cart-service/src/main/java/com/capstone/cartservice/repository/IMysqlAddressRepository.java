package com.capstone.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMysqlAddressRepository extends JpaRepository<Address, UUID> {
}
