package com.example.filters;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationLoggingFilter implements Filter{
	
	private Logger logger = Logger.getLogger(AuthenticationLoggingFilter.class.getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		var httpRequest = (HttpServletRequest) request;
		
		var httpResponse = (HttpServletResponse) response;
		
		// Gets the request ID from the request headers
		String requestId = httpRequest.getHeader("Request-Id");
		
		// Logs the event with the value of the request ID
		logger.info("Successfully authenticated request with id " + requestId);
		
		// Forwards the request to the next filter in the chain
		chain.doFilter(request, response);
		
	}
}
