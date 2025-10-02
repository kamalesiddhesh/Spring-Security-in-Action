package com.example.csrf.repo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import com.example.entities.Token;
import com.example.repos.JpaTokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Component
public class CustomCsrfTokenRepository implements CsrfTokenRepository{
	
	private JpaTokenRepository jpaTokenRepository;
	private static final int EXPIRY_MINUTES = 1;
	
	@Autowired
	public CustomCsrfTokenRepository(JpaTokenRepository jpaTokenRepository) {
		super();
		this.jpaTokenRepository = jpaTokenRepository;
	}

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		System.out.println("generateToken Method uuid : " + uuid);
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}

	@Override
	public void saveToken(CsrfToken csrfToken, HttpServletRequest request, HttpServletResponse response) {
		String identifier =  request.getHeader("X-IDENTIFIER");
		System.out.println("saveToken method Identifier : " + identifier);
		Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
		
		if(existingToken.isPresent()) {
			Token token = existingToken.get();
			token.setToken(csrfToken.getToken());
			token.setTimestamp(LocalDateTime.now());
            jpaTokenRepository.save(token);
		}
		else {
			Token token = new Token();
			token.setToken(csrfToken.getToken());
			token.setIdentifier(identifier);
			token.setTimestamp(LocalDateTime.now()); // Set the timestamp
            
			jpaTokenRepository.save(token);
		}
		
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		String identifier =  request.getHeader("X-IDENTIFIER");
		System.out.println("loadToken method Identifier : " + identifier);
		Optional<Token> existingToken = jpaTokenRepository.findTokenByIdentifier(identifier);
		
		if(existingToken.isPresent()) {
			Token token = existingToken.get();
			
			// For CSRF Expired token approach
			// Check if the token has expired			
            if (token.getTimestamp().plusMinutes(EXPIRY_MINUTES).isBefore(LocalDateTime.now())) {
                System.out.println("Token expired. Deleting from DB.");
                jpaTokenRepository.delete(token); // Delete expired token
                return null;
            }
            
			return new DefaultCsrfToken("X-CSRF-TOKEN","_csrf",token.getToken());
		}
		return null;		
	}

}
