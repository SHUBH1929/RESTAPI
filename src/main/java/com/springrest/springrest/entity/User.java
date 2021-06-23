package com.springrest.springrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	
	@OneToOne(mappedBy = "user",cascade=CascadeType.ALL)
	private Token token;
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	private List<PlayList> playList;
	

	public List<PlayList> getPlayList() {
		return playList;
	}


	public void setPlayList(List<PlayList> playList) {
		this.playList = playList;
	}


	public User( String email, String password) {
		this.email = email;
		this.password = password;
	}


	public User() {

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Token getToken() {
		return token;
	}


	public void setToken(Token token) {
		this.token = token;
	}


	

}
