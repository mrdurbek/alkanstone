package com.alkan.AlkanStone.security;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.alkan.AlkanStone.entity.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider  {
	
	private String secret = "qwertyuiop987654321@#";
	private long expireTime = 3600000*24*10;
		
	public String generateToken(String username , Set<Role> roles) {
		
		String token = Jwts
			
						.builder()
						.setSubject(username)
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis()+expireTime))
						.claim("roles", roles)
						.signWith(SignatureAlgorithm.HS512 , secret)
						.compact();
			
		return token;
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts
			.parser()
			.setSigningKey(secret)
			.parseClaimsJws(token);
	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUsername(String token) {
		String username = Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
		return username;
	}
}
