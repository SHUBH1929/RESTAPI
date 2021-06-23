package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entity.Token;
@Repository
public interface TokenDao extends JpaRepository<Token, Integer>{
	
	public Token findByToken(String token);

}
