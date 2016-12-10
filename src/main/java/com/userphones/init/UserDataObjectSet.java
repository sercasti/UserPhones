package com.userphones.init;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.userphones.entities.Phone;
import com.userphones.entities.User;
import com.userphones.models.PhoneDao;
import com.userphones.models.UserDao;

/**
 * Initial object set to insert data in case DB is empty
 */
@Component
public class UserDataObjectSet {

	@Autowired
	private UserDao userDao;

	private static PhoneDao phoneDao;

	private static Random generator = new Random();

	@Autowired
	public UserDataObjectSet(PhoneDao phoneDao) {
		UserDataObjectSet.phoneDao = phoneDao;
	}

	@EventListener(ContextRefreshedEvent.class)
	void contextRefreshedEvent() {
		if (userDao.count() < 1) {
			userDao.save(getSampleUsers());
		}
	}

	private static Set<User> getSampleUsers() {
		Set<User> users = new HashSet<User>();
		users.add(new User("Charles Jefferson", "cf@google.com", getSamplePhones()));
		users.add(new User("Richard Nichols", "rn@google.com", getSamplePhones()));
		users.add(new User("Michael Sims", "ms@google.com", getSamplePhones()));
		users.add(new User("Robert Openbach", "ro@google.com", getSamplePhones()));
		users.add(new User("James Gillet", "jg@google.com", getSamplePhones()));
		users.add(new User("John Butnick", "jb@google.com", getSamplePhones()));
		users.add(new User("Danny", "danny@google.com", getSamplePhones()));
		return users;
	}

	private static Set<Phone> getSamplePhones() {
		Set<Phone> phones = new HashSet<Phone>();
		for (int i = generator.nextInt(4); i > 0; i--) {
			phones.add(getRandomPhone(i));
		}
		return phones;
	}

	private static Phone getRandomPhone(int index) {
		Phone phone = new Phone(getRandomPhoneNumber(), getRandomDescription(index));
		phoneDao.save(phone);
		return phone;
	}

	private static String getRandomPhoneNumber() {
		int num1, num2, num3; // 3 numbers in area code
		int set2, set3; // sequence 2 and 3 of the phone number

		num1 = generator.nextInt(7) + 1; // add 1 so there is no 0 to begin
		num2 = generator.nextInt(8); // randomize to 8 because 0 counts as a
										// number in the generator
		num3 = generator.nextInt(8);
		set2 = generator.nextInt(643) + 100;
		set3 = generator.nextInt(8999) + 1000;
		return num1 + "" + num2 + "" + num3 + "-" + set2 + "-" + set3;
	}

	private static String getRandomDescription(int index) {
		String[] descriptions = { "Home", "Cell", "Emergency Contact", "New York Office", "Florida Office" };
		return descriptions[index];
	}

}
