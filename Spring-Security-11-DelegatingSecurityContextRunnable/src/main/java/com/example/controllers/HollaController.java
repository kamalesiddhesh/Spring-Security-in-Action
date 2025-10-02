package com.example.controllers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HollaController {
	
	@GetMapping("/hola")
	public String hola() throws Exception {
		
		Callable<String> task = () -> {
			SecurityContext context = SecurityContextHolder.getContext();
			return context.getAuthentication().getName();
		};
		
		ExecutorService e = Executors.newCachedThreadPool();
		e = new  DelegatingSecurityContextExecutorService(e);
		
		try {
			return "Hola " + e.submit(task).get() + " !";
		}
		finally {
			e.shutdown();
			
		}
		
	}

}
