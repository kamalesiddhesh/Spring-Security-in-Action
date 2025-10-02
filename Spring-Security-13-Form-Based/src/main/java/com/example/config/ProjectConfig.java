package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.formLogin(Customizer.withDefaults());
		http.formLogin(c -> c.defaultSuccessUrl("/home", true));
		http.authorizeHttpRequests(c-> c
										.requestMatchers("/home", "/error", "/css/**", "/js/**", "/images/**").permitAll()
										.anyRequest().authenticated());
		
		return http.build();
	}
}
