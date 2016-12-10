package com.userphones.integration;

import static io.restassured.RestAssured.when;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.userphones.config.Application;
import com.userphones.entities.Phone;
import com.userphones.entities.User;
import com.userphones.models.PhoneDao;
import com.userphones.models.UserDao;

import io.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserPhonesRestIntegrationTest {

	private static final String PHONE_TYPE = "Home";
	@Autowired
	UserDao repository;
	@Autowired
	PhoneDao phoneRepository;
	@LocalServerPort
	int randomPort;
	User mickey;
	User pluto;

	@Before
	public void setUp() {
		Phone mickeyPhone = new Phone("212-123-1234", PHONE_TYPE);
		mickey = new User("Mickey", "mm@gmail.com", new HashSet<Phone>(Arrays.asList(mickeyPhone)));
		Phone plutoPhone = new Phone("212-123-1235", PHONE_TYPE);
		pluto = new User("Pluto", "mm@gmail.com", new HashSet<Phone>(Arrays.asList(plutoPhone)));

		repository.deleteAll();
		phoneRepository.save(Arrays.asList(mickeyPhone, plutoPhone));
		repository.save(Arrays.asList(mickey, pluto));

		RestAssured.port = randomPort;
	}

	@Test
	public void canFetchMickey() {
		when().get("/userPhones/v1/phones/{name}", "Mickey").then().statusCode(HttpStatus.SC_OK)
				.body("number", Matchers.equalTo(Arrays.asList("212-123-1234")))
				.body("description", Matchers.is(Arrays.asList(PHONE_TYPE)));
	}

	@Test
	public void canFetchPlutoDesc() {
		when().get("userPhones/v1/phones/{name}/{description}", "Pluto", PHONE_TYPE).then().statusCode(HttpStatus.SC_OK)
				.body("number", Matchers.equalTo(Arrays.asList("212-123-1235")))
				.body("description", Matchers.is(Arrays.asList(PHONE_TYPE)));
	}

}