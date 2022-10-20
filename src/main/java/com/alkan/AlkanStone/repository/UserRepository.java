package com.alkan.AlkanStone.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	boolean existsByUsername(String username);
	
	Optional<User> findByUsername(String username);
}
