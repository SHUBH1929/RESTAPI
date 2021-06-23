package com.springrest.springrest.controller;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.service.PlayListSongService;

@RestController
public class PlayListSongController {

	@Autowired
	private PlayListSongService playListSongService;
	

	@PostMapping("/addsongtoplaylist")
	public String addSongToPlaylist(@RequestParam("playlistname") String uname,
			@RequestBody HashSet<String>songlst,@RequestHeader("token")String token) {
		return this.playListSongService.addSong(uname,songlst,token);
	}
	@PutMapping("/deletesongs")
	public String deleteSong(@RequestParam("playlistname") String uname,
			@RequestBody HashSet<String>songlst,@RequestHeader("token")String token) {
		return this.playListSongService.removeSong(uname,songlst,token);
	}
	
}
