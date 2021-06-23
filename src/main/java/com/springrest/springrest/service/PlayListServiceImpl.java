package com.springrest.springrest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.PlayListDao;
import com.springrest.springrest.dao.TokenDao;
import com.springrest.springrest.dao.UserDao;
import com.springrest.springrest.entity.PlayList;
import com.springrest.springrest.entity.PlayListSong;
import com.springrest.springrest.entity.Song;
import com.springrest.springrest.entity.Token;
import com.springrest.springrest.entity.User;

@Service
public class PlayListServiceImpl implements PlayListService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private PlayListDao playListDao;

	@Override
	public String addPlayList(String name, String token) {
		Token t = tokenDao.findByToken(token);
		if (t == null)
			return "User is not logged in (Incorrect Token)";
        
		User user = t.getUser();
		
		// creating PlayList to add in the user
		List<PlayList> playList_lst = new ArrayList<PlayList>();
		PlayList p = new PlayList();

		// random string generation

		String n = name.substring(0, 5);
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(5);
		for (int i = 0; i < 5; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		String uname = sb.toString();

		// saving all the data
		p.setName(name);
		p.setUniqueName(n + uname);
		p.setUser(user);
		playList_lst.add(p);
		user.setPlayList(playList_lst);
		userDao.save(user);
		return "playlist " + name + " and Uniquename " + n + uname + " successfully Created";
	}

	@Override
	public String updatePlayListName(String playListUname, String newName, String token) {
		
		Token user_token = tokenDao.findByToken(token);
		if (user_token == null)
			return null;
		PlayList user_PlayList = playListDao.findByUniqueNameAndUserId(playListUname, user_token.getUser().getId());
		if (user_PlayList == null)
			return null;

		String n = playListUname.substring(0, 5);
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(5);
		for (int i = 0; i < 5; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		String un = sb.toString();
		String previousName = user_PlayList.getName();
		user_PlayList.setUniqueName(newName);
		user_PlayList.setUniqueName(n + un);
		playListDao.save(user_PlayList);
		return "Name Succesfully Changed from " + previousName + " to " + newName;
	}

	@Override
	public List<PlayList> showAllPlayList(String token) {
		List<PlayList> lst = new ArrayList<PlayList>();
		Token t = tokenDao.findByToken(token);
		if (t == null)
			return lst;
		lst = playListDao.findAllByUserId(t.getUser().getId());
		System.out.println(lst);
		return lst;
	}

	@Override
	public List<Song> showAllSongs(String playListUname, String token) {

		Token user_token = tokenDao.findByToken(token);
		if (user_token == null)
			return null;
		PlayList user_PlayList = playListDao.findByUniqueNameAndUserId(playListUname, user_token.getUser().getId());
		if (user_PlayList == null)
			return null;
		
			List<PlayListSong> lst = user_PlayList.getPlayListSong();
			List<Song> songlst = new ArrayList<Song>();
			for (PlayListSong pls : lst) {
				songlst.add(pls.getSong());
			}
			return songlst;
		}

	}

