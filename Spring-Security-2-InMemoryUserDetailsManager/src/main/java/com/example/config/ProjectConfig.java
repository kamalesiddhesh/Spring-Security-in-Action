package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
		
	// Customized UserDetailsService
	@Bean
	UserDetailsService userDetailsService() {
		var user = User.withUsername("john")
					   .password("12345")
					   .authorities("read")
					   .build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	// Customized PasswordEncoder 
    @Bean
    PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
    
    // Option 1 - Separately added UserDetailsService 
    
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception{
	  
    	  //App uses HTTP Basic authentication
		  http.httpBasic(Customizer.withDefaults());
	  
		  // All the requests require authentication 
		  http.authorizeHttpRequests( 
		  			// All the requests require authentication. 
		  			c -> c.anyRequest().authenticated()
	  
		  			// None of the requests need to be authenticated. 
		  			// c ->	  c.anyRequest().permitAll() 
		  			);
	  
		  return http.build(); 
	 }
	 
// -----------------------------------------------------------------------------------------------------------------	
    
    // Option 2 - Adding UserDetailsService in SecurityFilterChain itself
    
    
	/*
	 * @Bean SecurityFilterChain configure(HttpSecurity http) throws Exception{
	 * 
	 * //App uses HTTP Basic authentication
	 * http.httpBasic(Customizer.withDefaults());
	 * 
	 * // All the requests require authentication http.authorizeHttpRequests( // All
	 * the requests require authentication. c -> c.anyRequest().authenticated()
	 * 
	 * // None of the requests need to be authenticated. // c ->
	 * c.anyRequest().permitAll() );
	 * 
	 * var user = User.withUsername("john") .password("12345") .build();
	 * 
	 * var userDetailsService = new InMemoryUserDetailsManager(user);
	 * 
	 * http.userDetailsService(userDetailsService);
	 * 
	 * return http.build(); }
	 */
    

}
