package com.project.self.crud.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "users")
public class Users {

	@Id
	private String id;
	@Indexed
	private String fname;
	private String lname;
	private String address;
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	
	public Users() {
		id = UUID.randomUUID().toString(); 
	}

	public Users(String id, String fname, String lname, String address, LocalDateTime createdAt, LocalDateTime modifiedAt) {
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address=" + address + ", createdAt="
				+ createdAt + ", modifiedAt=" + modifiedAt + "]";
	}
	
}
