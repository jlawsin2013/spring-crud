package com.project.self.crud.service;

import java.util.List;
import java.util.Map;

import com.project.self.crud.model.Users;

public interface UserService {
	public String save(Users user);
	public List<Users> getAllUsers();
	public Users getUsersById(String id);
	public void update(String id, Map<Object, Object> predicates);
	public void deleteUser(String id);
}
