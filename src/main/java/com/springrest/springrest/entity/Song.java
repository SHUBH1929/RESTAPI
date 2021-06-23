package com.springrest.springrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","playListSong"})
public class Song {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String uniqueName;
	

	@OneToMany(mappedBy = "song",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PlayListSong> playListSong;
	
	
	
	public List<PlayListSong> getPlayListSong() {
		return playListSong;
	}

	public void setPlayListSong(List<PlayListSong> playListSong) {
		this.playListSong = playListSong;
	}

	public Song() {
	}

	public Song(String name) {
		this.id=0;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueName() {
		return uniqueName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}



}
