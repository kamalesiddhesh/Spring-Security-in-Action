package com.example.authenticationproviders;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	public CustomAuthenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails u = userDetailsService.loadUserByUsername(username);
		
		
		if(passwordEncoder.matches(password, u.getPassword())) {
			// If the password matches, it returns an implementation of the Authentication contract with the necessary details
			return new UsernamePasswordAuthenticationToken(username,password,u.getAuthorities());
		}
		else {
			// If the password doesn’t match, it throws an exception of type AuthenticationException.
			// BadCredentialsException inherits from AuthenticationException.
			throw new BadCredentialsException("Something went wrong!!");
		}
	}

	@Override
	public boolean supports(Class<?> authenticationType) {		
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
		
	}

}
