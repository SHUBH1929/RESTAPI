package com.springrest.springrest.service;

import java.util.List;

import com.springrest.springrest.entity.PlayList;
import com.springrest.springrest.entity.Song;

public interface PlayListService {
	public String addPlayList(String name,String token);
	public String updatePlayListName(String uname,String newName ,String token);
	public List<PlayList> showAllPlayList(String token);
	public List<Song> showAllSongs(String uname,String token);
}
