package com.example.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class InMemoryUserDetailsService  implements UserDetailsService{
	
	private final List<UserDetails> users;
	
	public InMemoryUserDetailsService(List<UserDetails> users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		return users.stream().filter(
					u -> u.getUsername().equals(username)
				)
				.findFirst()   // If there is such a user, returns it
				.orElseThrow(
					() -> new UsernameNotFoundException("User not found") // If a user with this username does not exist, throws an exception
				);
		
	}
}
