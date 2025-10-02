package com.example.authentication.successhandlers;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) 
			throws IOException, ServletException {
		var authorities = authentication.getAuthorities();
		
		// Returns an empty Optional object if the 	“read” authority doesn’t exist
		var auth = authorities.stream()
							  .filter(a -> a.getAuthority().equals("read"))
							  .findFirst(); 
		
		// If the “read” authority exists, it redirects to 	/home.		
		if(auth.isPresent()) {
			response.sendRedirect("/home");
		}
		else {
			response.sendRedirect("/error");
		}		
	}	
}
