package com.alkan.AlkanStone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkan.AlkanStone.entity.User;
import com.alkan.AlkanStone.repository.UserRepository;

@Service
public class MyUserDetailsService  implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if(optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("Error occured");
		}
		return optionalUser.get();
	}

}
