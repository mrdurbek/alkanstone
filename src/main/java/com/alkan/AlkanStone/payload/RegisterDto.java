package com.alkan.AlkanStone.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {
	
	@NotNull
	@Size(min = 3 , max = 30)
	private String firstname;
	@NotNull
	@Size(min = 3 , max = 30)
	private String lastname;
	@NotNull
	@Size(min = 3 , max = 30)
	private String username;
	@NotNull
	@Size(min = 8)
	private String password;
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
	
	
}
