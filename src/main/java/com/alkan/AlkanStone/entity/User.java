package com.alkan.AlkanStone.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "users")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String firstname;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false , unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	Set<Role> roles;
	
	private boolean accountNonExpired = true;
	private boolean accountNonLocked  = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	
}
