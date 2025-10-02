package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.authentication.failedhandlers.CustomAuthenticationFailureHandler;
import com.example.authentication.successhandlers.CustomAuthenticationSuccessHandler;

import lombok.Data;

@Configuration
@Data
public class ProjectConfig {
	
	private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	private final CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService uds() {
		UserDetailsManager uds = new InMemoryUserDetailsManager();
		
		// Option 1
		// Creating user1 (UserDetails) using User class 
		UserDetails user1 = User.withDefaultPasswordEncoder()
								.username("john")
								.password("12345")
								.authorities("read")
								.build();
		
		uds.createUser(user1);
		
		// Option 2
		uds.createUser(
				User.withDefaultPasswordEncoder()
					.username("bill")
					.password("12345")
					.authorities("write")
					.build()				
				);
		
		return uds;
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.formLogin(c -> c.successHandler(authenticationSuccessHandler)
							 .failureHandler(authenticationFailureHandler)
					  );
		
		http.httpBasic(Customizer.withDefaults());
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
		
		return http.build();
	}	
}
