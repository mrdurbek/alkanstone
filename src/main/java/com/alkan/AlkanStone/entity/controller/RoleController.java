package com.alkan.AlkanStone.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkan.AlkanStone.entity.Role;import com.alkan.AlkanStone.entity.enums.RoleName;
import com.alkan.AlkanStone.repository.RoleRepository;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody String name){
		Role role = new Role();
		if(name == "USER") role.setName(RoleName.ROLE_USER);
		else role.setName(RoleName.ROLE_ADMIN);
		roleRepository.save(role);;
		return ResponseEntity.ok("Role is existed");
	}
}
