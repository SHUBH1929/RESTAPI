package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entity.Song;
@Repository
public interface SongDao extends JpaRepository<Song, Integer>{
	public Song findByUniqueName(String Uname);
	
}
