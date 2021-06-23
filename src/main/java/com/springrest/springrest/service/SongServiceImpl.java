package com.springrest.springrest.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.SongDao;
import com.springrest.springrest.entity.Song;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
	private SongDao songDao;


	@Override
	public List<Song> getSong() {
		return songDao.findAll();
	}

	@Override
	public String addSong(List<String> songnamelst) {
		
		for(String songname:songnamelst)
		{
		String n=songname.substring(0,5);
		String chars = "abcdefghijklmnopqrstuvwxyz";
				Random rnd = new Random();
				StringBuilder sb = new StringBuilder(5);
				for (int i = 0; i < 5; i++)
					sb.append(chars.charAt(rnd.nextInt(chars.length())));
		String uname=sb.toString();
		Song song = new Song();
		song.setName(songname);
		song.setUniqueName(n+uname);
		songDao.save(song);
		}
		return "Successfully Added";
	}

}
