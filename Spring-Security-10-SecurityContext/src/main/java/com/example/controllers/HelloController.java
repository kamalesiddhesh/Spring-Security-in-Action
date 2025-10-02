package com.example.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		// Obtaining the SecurityContext from the SecurityContextHolder
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		
		return "Hello, " + a.getName() + "!";
	}	
	
	@GetMapping("/hello2")
	// Spring Boot injects the current Authentication in the method parameter 
	public String hello(Authentication a) {
		return "Hello, " + a.getName() + "!";
	}
	
	@GetMapping("/bye")
	@Async
	public void goodbye() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication a = context.getAuthentication();
		String username = context.getAuthentication().getName();
		
	 // do something with the username
		System.out.println(username);
		
	}

}
