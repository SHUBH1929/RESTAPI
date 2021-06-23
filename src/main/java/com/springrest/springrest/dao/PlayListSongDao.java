package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entity.PlayListSong;
@Repository
public interface PlayListSongDao extends JpaRepository<PlayListSong, Integer>{

	public PlayListSong findByPlayListIdAndSongId(int playListId,int songId);
}
