package com.project.self.crud.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.self.crud.dto.UserCreateDto;
import com.project.self.crud.dto.UserRecordDto;
import com.project.self.crud.model.Users;
import com.project.self.crud.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody @Valid UserCreateDto user){
		return new ResponseEntity<>("A new user is created successfully: "+ service.save(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserRecordDto>> getUsers(){
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserRecordDto> getUsersByLname(@PathVariable("id") String id){
		return new ResponseEntity<>(service.getUsersById(id), HttpStatus.OK);
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable("id") String id, @RequestBody final UserCreateDto user) throws Exception {
		Map<Object, Object> updatePredicates = new HashMap<Object, Object>();
		
		for (final Field field : Users.class.getDeclaredFields()) {
			final String fieldNm = field.getName();
			
			if (fieldNm == "id") continue;
			
			final Method getter = Users.class.getDeclaredMethod("get" + StringUtils.capitalize(fieldNm));
			final Object fieldValue = getter.invoke(user);
			
			if (Objects.nonNull(fieldValue)) {
				updatePredicates.put(fieldNm, fieldValue);
			}
		}
		
		service.update(id, updatePredicates);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUserById(@PathVariable("id") String id){
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
