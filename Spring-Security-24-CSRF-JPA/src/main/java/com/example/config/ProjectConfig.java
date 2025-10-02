package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.csrf.repo.CustomCsrfTokenRepository;
import com.example.requestmatcher.MyCustomRequestMatcher;

import lombok.Data;

@Configuration
@Data
public class ProjectConfig {

	private final CustomCsrfTokenRepository customTokenRepository;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(
			// Uses the Customizer<CsrfConfigurer<HttpSecurity>>
			// object to plug the new CsrfTokenRepository
		    // implementation into the CSRF protection mechanism	
			c -> {
				c.csrfTokenRepository(customTokenRepository);
				// CSRF protection implementation by default wouldn't trigger for GET method hence
				// I customized the csrfprotection matcher to GET calls
//				c.requireCsrfProtectionMatcher(request -> request.getMethod().equals("GET") || request.getMethod().equals("POST"));
				c.requireCsrfProtectionMatcher(new MyCustomRequestMatcher());
				c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()); // Setting the CsrfTokenRequestAttributeHandler
																				   // object to manage the setup of the CSRF token on the HTTP request
				}	
				
			);
		
		http.authorizeHttpRequests(
				c -> c.anyRequest().permitAll()
				);
		return http.build();
	}

}
