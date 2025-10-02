package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.filters.AuthenticationLoggingFilter;
import com.example.filters.RequestValidationFilter;

@Configuration
public class ProjectConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
			// Adds an instance of the custom filter before the authentication filter in the filter chain
		http.addFilterBefore(new RequestValidationFilter(),BasicAuthenticationFilter.class)
			
			// Adds an instance of AuthenticationLoggingFilter to the filter chain after the authentication filter
			.addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
			
			.authorizeHttpRequests( c -> c.anyRequest().permitAll()
		);
		
		return http.build();
	}

}
