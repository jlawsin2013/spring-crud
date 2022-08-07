package com.project.self.crud.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.self.crud.model.Users;

public interface UsersRepository extends MongoRepository<Users, Integer> {

}
