package com.springrest.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    public User findByEmailAndPassword(String email,String Password);
}
