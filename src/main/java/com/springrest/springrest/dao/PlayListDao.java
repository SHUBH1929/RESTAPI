package com.springrest.springrest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entity.PlayList;
@Repository
public interface PlayListDao extends JpaRepository<PlayList, Integer>{
	
	public List<PlayList> findAllById(int id);
	public PlayList findByUniqueName(String playListUname);
	public List<PlayList> findAllByUserId(int id);
	public PlayList findByUniqueNameAndUserId(String playListUname, int id);

}
