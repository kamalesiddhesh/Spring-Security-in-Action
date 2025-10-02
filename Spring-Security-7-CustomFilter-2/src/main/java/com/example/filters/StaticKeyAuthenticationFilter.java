package com.example.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//To allow us to inject values from the properties file, it adds an instance of the class in the Spring context.
@Component
// Defines the authentication logic by implementing the Filter interface and overriding the doFilter() method
public class StaticKeyAuthenticationFilter implements Filter{
	
	// Takes the value of the static key from the properties file using the @Value annotation
	@Value("${authorization.key}")
	private String authorizationKey;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;
		
		System.out.println("authentication key from propeties : "+authorizationKey);
		
		// Takes the value of the Authorization header from the request to compare it with the static key 
		String authentication = httpRequest.getHeader("Authorization");
		System.out.println("authentication header : "+authentication);
		
		if(authorizationKey.equals(authentication)) {
			System.out.println(authorizationKey.equals(authentication));
			chain.doFilter(request, response);
		}
		else {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}
}
