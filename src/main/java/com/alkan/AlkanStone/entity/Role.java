package com.alkan.AlkanStone.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import com.alkan.AlkanStone.entity.enums.RoleName;

@Entity
public class Role implements GrantedAuthority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private RoleName name;
	
	@Override
	public String getAuthority() {
		return name.name();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	public String getName() {
		return name.name();
	}

	
	
	
}
