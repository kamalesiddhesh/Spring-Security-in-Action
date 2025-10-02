package com.example.filters;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// Instead of implementing the Filter interface, it extends the OncePerRequestFilter class.
public class AuthenticationLoggingFilter extends OncePerRequestFilter{
	
	private final Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());
	
	
	// Overrides doFilterInternal(), which replaces the purpose of the doFilter() method of the Filter interface
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
		// The OncePerRequestFilter only supports HTTP filters. This is why the parameters are directly given as HttpServletRequest and HttpServletResponse.
		
		String requestId = request.getHeader("Request-Id");
		logger.info("Successfully authenticated request with id " +requestId);
		
		filterChain.doFilter(request, response);		
	}
}
