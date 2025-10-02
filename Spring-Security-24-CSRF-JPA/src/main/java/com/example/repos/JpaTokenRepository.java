package com.example.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Token;

@Repository
public interface JpaTokenRepository extends JpaRepository<Token, Integer>  {
	
	Optional<Token> findTokenByIdentifier(String identifier);

}
