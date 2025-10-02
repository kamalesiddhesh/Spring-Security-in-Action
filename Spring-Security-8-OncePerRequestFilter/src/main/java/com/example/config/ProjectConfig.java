package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.filters.AuthenticationLoggingFilter;


@Configuration
public class ProjectConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
			// Adds an instance of the custom filter after the authentication filter in the filter chain
		http.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
			
			.authorizeHttpRequests( c -> c.anyRequest().permitAll()
		);
		
		return http.build();
	}

}