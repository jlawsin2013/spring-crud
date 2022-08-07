package com.project.self.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.self.crud.dao.UsersRepository;
import com.project.self.crud.model.Users;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UsersRepository repository;
	
	@PostMapping("/users")
	public Users saveUser(@RequestBody Users user){
		return repository.save(user);
	}
	
	@GetMapping("/users")
	public List<Users> getUsers(){
		return repository.findAll();
	}
}
