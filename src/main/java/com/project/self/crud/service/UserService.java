package com.project.self.crud.service;

import java.util.List;
import java.util.Map;

import com.project.self.crud.dto.UserCreateDto;
import com.project.self.crud.dto.UserRecordDto;

public interface UserService {
	public String save(UserCreateDto user);
	public List<UserRecordDto> getAllUsers();
	public UserRecordDto getUsersById(String id);
	public void update(String id, Map<Object, Object> predicates);
	public void deleteUser(String id);
}
