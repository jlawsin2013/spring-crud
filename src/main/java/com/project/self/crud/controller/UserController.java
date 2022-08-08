package com.project.self.crud.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.self.crud.model.Users;
import com.project.self.crud.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl service;
	
	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody Users user){
		return new ResponseEntity<>("A new user is created successfully: "+ service.save(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getUsers(){
		return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{lname}")
	public ResponseEntity<List<Users>> getUsersByLname(@PathVariable("lname") String lname){
		return new ResponseEntity<>(service.getUsersByLname(lname), HttpStatus.OK);
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<String> updateUser(@PathVariable("id") String id, @RequestBody final Users user) throws Exception {
		for (final Field field : Users.class.getDeclaredFields()) {
			final String fieldNm = field.getName();
			
			if (fieldNm == "id") continue;
			
			final Method getter = Users.class.getDeclaredMethod("get" + StringUtils.capitalize(fieldNm));
			final Object fieldValue = getter.invoke(user);
			
			if (Objects.nonNull(fieldValue)) {
				service.update(id, fieldNm, fieldValue);
			}
		}

		return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
	}
}
