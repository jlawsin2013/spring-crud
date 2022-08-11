package com.project.self.crud.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String id) {
		super(String.format("User with an Id of %s is not found", id));
	}
}
