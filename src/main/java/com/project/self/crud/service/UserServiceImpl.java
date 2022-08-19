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
		Users userModel = new Users();
		userModel.setFname(userDto.getFname());
		userModel.setLname(userDto.getLname());
		userModel.setAddress(userDto.getAddress());
		
        return repository.save(userModel).getId();
    }

	@Override
	public List<UserRecordDto> getAllUsers() {
		List <UserRecordDto> userRecord = repository.findAll().stream().map(user -> new UserRecordDto(user.getId(), user.getFname(), user.getLname(), user.getAddress(), user.getCreatedAt(), user.getModifiedAt())).collect(Collectors.toList());
		return userRecord;
	}

	@Override
	public UserRecordDto getUsersById(String id) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		UserRecordDto userRecord = new UserRecordDto();
		
		if (user != null) {
			userRecord.setId(user.getId());
			userRecord.setFname(user.getFname());
			userRecord.setLname(user.getLname());
			userRecord.setAddress(user.getAddress());
			userRecord.setCreatedAt(user.getCreatedAt());
			userRecord.setModifiedAt(user.getModifiedAt());
		}
		
		return userRecord;
	}
	
	@Override
	public void deleteUser(String id) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		if (user.getId().equals(id)) {
			repository.deleteById(id);
		}
	}

	@Override
	public void update(String id, Map<Object, Object> updatePredicates) {
		Users user = repository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
		if (user.getId().equals(id)) {
			Update update = new Update();
			for (Map.Entry<Object, Object> entry : updatePredicates.entrySet()) {
				update.set(entry.getKey().toString(), entry.getValue());
			}
			
			template.findAndModify(BasicQuery.query(Criteria.where("id").is(id)), update, Users.class);
		}
	}
}
