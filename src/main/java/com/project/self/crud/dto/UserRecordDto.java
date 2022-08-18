package com.project.self.crud.dto;

import java.time.LocalDateTime;

public class UserRecordDto {

	private String id;
	private String fname;
	private String lname;
	private String address;
	private String createdAt;
	private String modifiedAt;
	
	public UserRecordDto() {
		
	}

	public UserRecordDto(String id, String fname, String lname, String address, String createdAt, String modifiedAt) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public String getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getModifiedAt() {
		return modifiedAt;
	}
	
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address=" + address + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
}
