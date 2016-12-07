package com.userphones.models;

import org.springframework.data.repository.CrudRepository;

import com.userphones.entities.User;

public interface UserDao extends CrudRepository<User, Integer> {

	public User findByName(String name);

}
