package com.alkan.AlkanStone.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.LoginDto;
import com.alkan.AlkanStone.payload.RegisterDto;
import com.alkan.AlkanStone.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthService authService;
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
		ApiResponse apiResponse = authService.register(registerDto);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
		ApiResponse apiResponse = authService.login(loginDto);
		if(apiResponse.isResponse()) {
			return ResponseEntity.ok(apiResponse.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse.getMessage());
	}
}
