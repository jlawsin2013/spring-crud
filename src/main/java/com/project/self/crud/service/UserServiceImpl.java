package com.project.self.crud.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.project.self.crud.dto.UserCreateDto;
import com.project.self.crud.dto.UserRecordDto;
import com.project.self.crud.model.Users;
import com.project.self.crud.exception.UserNotFoundException;
import com.project.self.crud.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UsersRepository repository;
	
	@Autowired
	private MongoTemplate template;
	
	@Override
    public String save(UserCreateDto userDto) {
        return repository.save(Users.builder().fname(userDto.getFname()).lname(userDto.getLname()).address(userDto.getAddress()).build()).getId();
    }

	@Override
	public List<UserRecordDto> getAllUsers() {
		List <UserRecordDto> userRecord = repository.findAll().stream().map(user -> UserRecordDto.builder().id(user.getId()).fname(user.getFname()).lname(user.getLname()).address(user.getAddress()).createdAt(user.getCreatedAt()).modifiedAt(user.getModifiedAt()).build()).collect(Collectors.toList());
		return userRecord;
	}

	@Override
	public UserRecordDto getUsersById(String id) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		return UserRecordDto.builder().id(user.getId()).fname(user.getFname()).lname(user.getLname()).address(user.getAddress()).createdAt(user.getCreatedAt()).modifiedAt(user.getModifiedAt()).build();
	}
	
	@Override
	public void deleteUser(String id) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		repository.deleteById(user.getId());
	}

	@Override
	public void update(String id, Map<Object, Object> updatePredicates) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		Update update = new Update();
		for (Map.Entry<Object, Object> entry : updatePredicates.entrySet()) {
			update.set(entry.getKey().toString(), entry.getValue());
		}
		
		template.findAndModify(BasicQuery.query(Criteria.where("id").is(id)), update, Users.class);
	}
}
