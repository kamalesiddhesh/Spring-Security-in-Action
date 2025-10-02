package com.example.authentication.failurehandlers;

import java.io.IOException;
import java.time.LocalDateTime;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
		try {
			response.setHeader("failed", LocalDateTime.now().toString());
			response.sendRedirect("/error");
		}
		catch(IOException e) {
			throw new RuntimeException(e);
		}		
	}
}
