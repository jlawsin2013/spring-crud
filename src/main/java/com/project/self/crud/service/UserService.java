package com.project.self.crud.service;

import java.util.List;

import com.project.self.crud.model.Users;

public interface UserService {
	public String save(Users user);
	public List<Users> getAllUsers();
	public List<Users> getUsersByLname(String lname);
	public void update(String id, String fieldNm, Object fieldValue);
}
