package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.filters.StaticKeyAuthenticationFilter;

@Configuration
public class ProjectConfig {
	
	// Injects the instance of the filter from the Spring context
	private final StaticKeyAuthenticationFilter filter;
	
	public ProjectConfig(StaticKeyAuthenticationFilter filter) {
		this.filter = filter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		// Adds the filter at the position of the basic authentication filter in the filter chain
		http.addFilterAt(filter,BasicAuthenticationFilter.class)
			.authorizeHttpRequests(c -> c.anyRequest().permitAll());		
		return http.build();
		
	}

}
