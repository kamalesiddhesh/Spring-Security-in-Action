package com.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class ProjectConfig {
	
	@Bean
	UserDetailsService userDetailsService(DataSource datasource) {
		return new JdbcUserDetailsManager(datasource);
		
		// 
		/*
		 	String usersByUsernameQuery =  "select username, password, enabled from users where username = ?"; 
		 	String authsByUserQuery =  "select username, authority from spring.authorities where username = ?";
		 	var userDetailsManager = new JdbcUserDetailsManager(dataSource); 
		 	userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
		 	userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
		 	return userDetailsManager;		 
		*/
		
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {		
		return NoOpPasswordEncoder.getInstance();
	}

}
