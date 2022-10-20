package com.alkan.AlkanStone.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.Role;
import com.alkan.AlkanStone.entity.User;
import com.alkan.AlkanStone.entity.enums.RoleName;
import com.alkan.AlkanStone.payload.ApiResponse;
import com.alkan.AlkanStone.payload.LoginDto;
import com.alkan.AlkanStone.payload.RegisterDto;
import com.alkan.AlkanStone.repository.RoleRepository;
import com.alkan.AlkanStone.repository.UserRepository;
import com.alkan.AlkanStone.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	
	public ApiResponse register(RegisterDto registerDto) {
		
		boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
		if(existsByUsername) {
			return new ApiResponse("This user is already existed with this username" , false);
		}
		
		User user = new User();
		user.setFirstname(registerDto.getFirstname());
		user.setLastname(registerDto.getLastname());
		user.setUsername(registerDto.getUsername());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setRoles(roleRepository.findAllByName(RoleName.ROLE_ADMIN));
		userRepository.save(user);
		return new ApiResponse("Succesfully existed" , true);
	}
	
	public ApiResponse login(LoginDto loginDto) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
			if(optionalUser.isEmpty()) {
				return new ApiResponse("Error occured" , false);
			}
			User user =  optionalUser.get();
			Set<Role> roles = user.getRoles();
			String token = jwtProvider.generateToken(loginDto.getUsername() , roles);
			return new ApiResponse(token , true);
		} catch (Exception e) {
			return new ApiResponse("login or password is incorrect", false );
		}
	}
}
