package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entity.PlayList;
import com.springrest.springrest.entity.Song;
import com.springrest.springrest.service.PlayListService;

@RestController
public class PlayListController {
	
	@Autowired
	private PlayListService playListServ;
	
	@PostMapping("/createplaylist")
	public String createPlaylist(@RequestParam("name") String name, @RequestHeader("token")String token) {
		return this.playListServ.addPlayList(name,token);
	}

	@PutMapping("/updateplaylist")
	public String logout(@RequestParam("playlistname") String uname, @RequestParam("newname") String newName,
			@RequestHeader("token")String token) {
		return this.playListServ.updatePlayListName(uname, newName, token);
	}

	@GetMapping("/allplaylist")
	public List<PlayList> allPlaylist(@RequestHeader("token")String token) {
		return this.playListServ.showAllPlayList(token);
	}
	@GetMapping("showallsongs")
	public List<Song> showAllSongs(@RequestParam("playlistname") String uname,@RequestHeader("token")String token)
	{
		
		return this.playListServ.showAllSongs(uname, token);
	}


}
