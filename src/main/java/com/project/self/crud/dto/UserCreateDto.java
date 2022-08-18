package com.project.self.crud.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCreateDto {

	@NotNull(message = "Firstname is required")
	@Size(min = 3,message = "Firstname should be at least 3 characters")
	private String fname;
	@NotNull(message = "Lastname is required")
	@Size(min = 3,message = "Lastname should be at least 3 characters")
	private String lname;
	@NotNull(message = "Address is required")
	private String address;

	public UserCreateDto(String fname, String lname, String address) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
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
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [" + ", fname=" + fname + ", lname=" + lname + ", address=" + address + "]";
	}

}
