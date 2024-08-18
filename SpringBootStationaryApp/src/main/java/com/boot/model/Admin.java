package com.boot.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name Cannot be blank")
	@Size(min = 4, max = 8, message = "Name should have 4 to 8 characters")
	@Pattern(regexp = "^[a-ZA-Z]+$", message = "Special character's not allowed")
	private String name;
	private String address;
	private String gender;
	private String username;
	private String password;
	public Admin(int id,
			@NotBlank(message = "Name Cannot be blank") @Size(min = 4, max = 8, message = "Name should have 4 to 8 characters") @Pattern(regexp = "^[a-ZA-Z]+$", message = "Special character's not allowed") String name,
			String address, String gender, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
