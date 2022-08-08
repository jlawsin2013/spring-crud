package com.project.self.crud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.self.crud.model.Users;

@Repository
public interface UsersRepository extends MongoRepository<Users, String> {
	List<Users> findByLnameStartsWith(String lname);
}
