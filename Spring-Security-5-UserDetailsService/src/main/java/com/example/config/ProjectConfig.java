package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.support.NoOpTaskScheduler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.model.User;
import com.example.services.InMemoryUserDetailsService;

@Configuration
public class ProjectConfig {
	
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails u = new User("john","12345","read");
		List<UserDetails> users = List.of(u);
		return new InMemoryUserDetailsService(users);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
