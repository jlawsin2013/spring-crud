package com.project.self.crud.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
	@NotNull(message = "Firstname is required")
	@Size(min = 3,message = "Firstname should be at least 3 characters")
	private String fname;
	@NotNull(message = "Lastname is required")
	@Size(min = 3,message = "Lastname should be at least 3 characters")
	private String lname;
	@NotNull(message = "Address is required")
	private String address;
}
