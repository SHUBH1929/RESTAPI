package com.springrest.springrest.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springrest.springrest.dao.TokenDao;
import com.springrest.springrest.dao.UserDao;
import com.springrest.springrest.entity.Token;
import com.springrest.springrest.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenDao tokenDao;

	@Override
	public String login(String email, String password) {
		User u = userDao.findByEmailAndPassword(email, password);
		if (u != null) {
			
			String chars = "123456789";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(20);
			for (int i = 0; i < 20; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			String token = sb.toString();
			
			//updating token on login
			
			Token t = u.getToken();
			t.setToken(token);
			tokenDao.save(t);
			return token;
		}
		return null;
	}

	@Override
	public String register(String email, String password) {
		if (userDao.findByEmail(email) == null) {
			User u = new User(email, password);
			
			
			//creating token on Register
			Token t = new Token();
			String chars = "123456789";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(20);
			for (int i = 0; i < 20; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			String token = sb.toString();
			
			
			t.setToken(token);
			t.setUser(u);
			u.setToken(t);
			userDao.save(u);
			return token;
		}
		return null;
	}

	@Override
	public String logout(String token) {
		Token t = tokenDao.findByToken(token);
		if (t == null)
			return null;
		String chars = "123456789";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(20);
		for (int i = 0; i < 20; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		String tok = sb.toString();
		t.setToken(tok);
		tokenDao.save(t);
		return tok;
	}

}
