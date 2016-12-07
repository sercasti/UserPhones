package com.userphones.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userphones.entities.Phone;
import com.userphones.models.PhoneDao;
import com.userphones.models.UserDao;

@RestController
public class UserPhonesRestController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PhoneDao phoneDao;

	//1.1 Get all phone numbers for Danny;
	@RequestMapping("{version}/phones/{username}")//1.3 How do you control the version of API? 
	public Set<Phone> getPhonesForUser(@PathVariable String version, @PathVariable String username, Device device) {
		return userDao.findByName(username).getPhones();
	}
 
	//1.2 Get Danny’s office’s phone numbers. 
	@RequestMapping("{version}/phones/{username}/{description}")
	public Set<Phone> getPhonesForUser(@PathVariable String version, @PathVariable String username,
			@PathVariable String description, Device device) {
		if (device.isNormal()) {
			//<!--1.4 How do you control the response for Mobile App or Website or Internal Service calls?--> 
		}else if (device.isMobile()){
			//do something different
		}
		return phoneDao.findByUsersAndDescriptionContaining(userDao.findByName(username), description);
	}

}