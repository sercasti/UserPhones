package com.userphones.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//1. Provide java class that can generate table in MYSQL 
@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	@JsonIgnore
	private int id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "email")
	private String email;

	// 2. Java class need to reflect this many to many relation in new db
	// design.
	@ManyToMany
	@JoinTable(name = "users_phones", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "phone_id"))
	@JsonManagedReference //bidirectionl Many to Many binding on jackson requires config 
	private Set<Phone> phones;

	protected User() {
	}

	public User(String name, String email, Set<Phone> phones) {
		super();
		this.name = name;
		this.email = email;
		this.phones = phones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

}