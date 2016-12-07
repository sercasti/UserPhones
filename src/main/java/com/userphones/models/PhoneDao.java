package com.userphones.models;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.userphones.entities.Phone;
import com.userphones.entities.User;

public interface PhoneDao extends CrudRepository<Phone, Integer> {

	public Set<Phone> findByUsersAndDescriptionContaining(User user, String description);

}
