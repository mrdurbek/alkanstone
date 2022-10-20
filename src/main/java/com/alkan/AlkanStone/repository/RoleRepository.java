package com.alkan.AlkanStone.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alkan.AlkanStone.entity.Role;
import com.alkan.AlkanStone.entity.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Set<Role> findAllByName(RoleName name);
}
