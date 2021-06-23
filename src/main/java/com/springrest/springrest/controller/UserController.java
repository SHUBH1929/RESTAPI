package com.springrest.springrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;


	@PostMapping("/register")
	public String register(@RequestParam("email") String email, @RequestParam("password") String password) {
		String token=this.userService.register(email, password);
		if (token!=null) {
			return token;
		}
		return "Email already Exist please Use Other Email";

	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		String token=this.userService.login(email, password);
		if (token!=null) {
			return token;
		}
		return "Wrong Email or Password";
	}

	@PutMapping("/logout")
	public String logout(@RequestHeader("token")String token) {
		String tok=this.userService.logout(token);
		if (tok!=null) {
			return tok;
		}
		return "The Given User Is Not Loged In";
	}
}
