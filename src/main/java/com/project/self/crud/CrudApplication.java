package com.project.self.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.self.crud.dao.UsersRepository;
import com.project.self.crud.model.Users;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class CrudApplication {
	
	@Autowired
	private UsersRepository repository;
	
	@PostMapping
	public Users saveUser(@RequestBody Users user){
		return repository.save(user);
	}
	
	@GetMapping
	public List<Users> getUsers(){
		return repository.findAll();
	}


	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
