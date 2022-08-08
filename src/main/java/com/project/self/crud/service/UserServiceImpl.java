package com.project.self.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.project.self.crud.model.Users;
import com.project.self.crud.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private MongoTemplate template;
	
	@Override
    public String save(Users user) {
        return repository.save(user).getId();
    }

	@Override
	public List<Users> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public List<Users> getUsersByLname(String lname) {
		return repository.findByLnameStartsWith(lname);
	}

	@Override
	public void update(String id, String fieldNm, Object fieldValue) {
		template.findAndModify(BasicQuery.query(Criteria.where("id").is(id)), BasicUpdate.update(fieldNm, fieldValue), Users.class);
	}
}
