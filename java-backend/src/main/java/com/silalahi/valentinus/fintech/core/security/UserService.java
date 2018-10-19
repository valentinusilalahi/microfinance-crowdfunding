package com.silalahi.valentinus.fintech.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.silalahi.valentinus.fintech.entity.User;
import com.silalahi.valentinus.fintech.repository.UserRepository;

@Component
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.map(this::toUserDetails)
				.orElseThrow(()->new UsernameNotFoundException("Username is not found"));
	}
	
	private UserDetails toUserDetails(User user) {
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getUserType())
				.build();
	}

}
