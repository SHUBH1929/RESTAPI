package com.springrest.springrest.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.PlayListDao;
import com.springrest.springrest.dao.PlayListSongDao;
import com.springrest.springrest.dao.SongDao;
import com.springrest.springrest.dao.TokenDao;
import com.springrest.springrest.entity.PlayList;
import com.springrest.springrest.entity.PlayListSong;
import com.springrest.springrest.entity.Song;
import com.springrest.springrest.entity.Token;

@Service
public class PlayListSongServiceImpl implements PlayListSongService {

	@Autowired
	private PlayListDao playListDao;
	@Autowired
	private SongDao songDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private PlayListSongDao playListSongDao;

	@Override
	public String addSong(String playListUname, HashSet<String> songlst, String token) {

		Token user_token = tokenDao.findByToken(token);
		if (user_token == null)
			return "User is not logged in (Incorrect Token)";
		PlayList user_playlist = playListDao.findByUniqueNameAndUserId(playListUname, user_token.getUser().getId());
		if (user_playlist == null)
			return "No Playlist Exist With given Name and User";

		List<Song> songs = new ArrayList<Song>();

		for (String songname : songlst) {
			// checking if song exist in the main list or not
			Song s = songDao.findByUniqueName(songname);
			if (s == null)
				return "No Song Found For Uname" + songname;

			// checking song exist in the playList or not
			if (playListSongDao.findByPlayListIdAndSongId(user_playlist.getId(),
					songDao.findByUniqueName(songname).getId()) == null)
				songs.add(s);
		}

		if (songs.size() == 0)
			return "all the songs are already added in the Playlist";
        
		List<PlayListSong> playListSong_lst = new ArrayList<PlayListSong>();
		for (Song song : songs) {
			PlayListSong playListSong_obj = new PlayListSong();
			playListSong_obj.setPlayList(user_playlist);
			playListSong_obj.setSong(song);
			playListSong_lst.add(playListSong_obj);
		}
		user_playlist.setPlayListSong(playListSong_lst);

		for (Song song : songs) {
			song.setPlayListSong(playListSong_lst);
			songDao.save(song);
		}
		playListDao.save(user_playlist);
		return "Succesfully Added All Songs To PlayList " + user_playlist.getName();
	}

	@Override
	public String removeSong(String playListUname, HashSet<String> songNamelst, String token) {
		
		Token user_token = tokenDao.findByToken(token);
		if (user_token == null)
			return "User is not logged in (Incorrect Token)";
		
		PlayList user_playlist = playListDao.findByUniqueNameAndUserId(playListUname, user_token.getUser().getId());
		if (user_playlist == null)
			return "No Playlist Exist With given Name and User";
		
		
		
		for (String songname : songNamelst) {
			Song song = songDao.findByUniqueName(songname);
			if (song != null) {
				PlayListSong pls = playListSongDao.findByPlayListIdAndSongId(user_playlist.getId(), song.getId());
				if (pls != null)
					playListSongDao.delete(pls);
			}
		}
		return "succesfuly deleted the songs";

	}

}
