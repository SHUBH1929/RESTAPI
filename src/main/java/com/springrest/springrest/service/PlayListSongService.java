package com.springrest.springrest.service;

import java.util.HashSet;

public interface PlayListSongService {
	
	public String addSong(String playListUname, HashSet<String> songlst, String token);

	String removeSong(String playListUname, HashSet<String> songlst, String token);

}
