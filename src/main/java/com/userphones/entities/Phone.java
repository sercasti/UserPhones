package com.userphones.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

//1. Provide java class that can generate table in MYSQL 
@Entity
@Table(name = "PHONES")
public class Phone {
	@Id
	@GeneratedValue
	@Column(name = "phone_id")
	@JsonIgnore
	private int id;

	@Column(name = "phone_number", nullable = false)
	private String number;

	@Column(name = "description")
	private String description;

	@ManyToMany(mappedBy = "phones")
	@JsonBackReference
	private Set<User> users;
	
	protected Phone(){
	}
	
	public Phone(String number, String description) {
		super();
		this.number = number;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}