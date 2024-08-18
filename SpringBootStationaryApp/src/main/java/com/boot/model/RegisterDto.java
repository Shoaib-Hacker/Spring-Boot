package com.boot.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {
	@NotEmpty
	private String fname;
	@NotEmpty
	private String lname;
	@Size(min=6 ,message ="Minimum password length is 6 characters")
	private String password;
	
	private String confirmpassword;

	@NotEmpty
	@Email
	private String email;
	private String address;
	private Long Phone;
	public RegisterDto(@NotEmpty String fname, @NotEmpty String lname,
			@Size(min = 6, message = "Minimum password length is 6 characters") String password, String confirmpassword,
			@NotEmpty @Email String email, String address, Long phone) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.email = email;
		this.address = address;
		Phone = phone;
	}
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhone() {
		return Phone;
	}
	public void setPhone(Long phone) {
		Phone = phone;
	}
	
	

}
