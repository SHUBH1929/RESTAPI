package com.springrest.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entity.Song;
import com.springrest.springrest.service.SongService;
@RestController
public class SongController {

	@Autowired
	private SongService songService;

	@GetMapping("/allsong")
	public List<Song> getSong() {
		return this.songService.getSong();

	}

	@PostMapping("/addsong")
	public String addSong(@RequestBody List<String> songnamelst) {
		return this.songService.addSong(songnamelst);

	}
	@PutMapping("/updatesong")
	public String updateSong(@RequestBody List<String> songnamelst) {
		return this.songService.addSong(songnamelst);

	}

}
