package com.springrest.springrest.service;

public interface UserService {
	public String login(String email,String password);
	public String register(String email,String password);
	public String logout(String token);
	
}
