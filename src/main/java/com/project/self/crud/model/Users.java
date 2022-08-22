package com.project.self.crud.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
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
}
