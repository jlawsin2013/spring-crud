package com.project.self.crud.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
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
	public void deleteUser(String id) {
		repository.deleteById(id);
	}

	@Override
	public String update(String id, Map<Object, Object> updatePredicates) {
		Update update = new Update();
		for (Map.Entry<Object, Object> entry : updatePredicates.entrySet()) {
			update.set(entry.getKey().toString(), entry.getValue());
		}
		
		return template.findAndModify(BasicQuery.query(Criteria.where("id").is(id)), update, Users.class).getId();
	}
}
