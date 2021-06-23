package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.entity.Song;

public interface SongService {
	public List<Song> getSong();
	public String addSong(List<String> songname);

}
