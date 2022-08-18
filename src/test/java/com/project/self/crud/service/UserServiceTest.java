package com.project.self.crud.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.self.crud.dto.UserCreateDto;
import com.project.self.crud.dto.UserRecordDto;
import com.project.self.crud.exception.UserNotFoundException;
import com.project.self.crud.model.Users;
import com.project.self.crud.repository.UsersRepository;


@SpringBootTest
public class UserServiceTest {
	
	@Autowired
	private UserServiceImpl service;
	
	@MockBean
	private UsersRepository repository;
	
	Users user;
	UserCreateDto userCreate;
	
	String expectedId;
	Map<Object, Object> predicates;
	
	@BeforeEach
	void setup() {
		LocalDateTime now = LocalDateTime.now(); 
		expectedId = "1";
		user = new Users("1", "John", "Lawsin", "PH", now, now);
		userCreate = new UserCreateDto("John", "Lawsin", "PH");
		
		predicates = new HashMap<Object, Object>() {{
		    put("fname", "Joshua");
		}};
	}
	
	@Test
	@DisplayName("Test getUsersById Success")
	void testGetUsersById() {
		Optional<Users> existingUser = Optional.of(user);
		when(repository.findById(any(String.class))).thenReturn(existingUser);
		UserRecordDto userRecord = service.getUsersById(user.getId());
        Assertions.assertEquals(expectedId, userRecord.getId());
	}
	
	@Test
	@DisplayName("Test getUsersById Failed")
	void testGetUsersByIdShouldThrowNotFoundException() {
		Optional<Users> emptyResult = Optional.empty();
		when(repository.findById(any(String.class))).thenReturn(emptyResult);
		Exception exception = Assertions.assertThrows(UserNotFoundException.class,
				() -> service.getUsersById(user.getId()));
		Assertions.assertEquals(String.format("User with an Id of %s is not found", expectedId), exception.getMessage());
	}
	
	@Test
	@DisplayName("Test getAllUsers Success")
	void testGetAllUsers() {
		List<Users> existingUser = List.of(user);
		when(repository.findAll()).thenReturn(existingUser);
		List<UserRecordDto> userRecord = service.getAllUsers();
        Assertions.assertEquals(1, userRecord.size());
	}
	
	@Test
	@DisplayName("Test save Success")
	void testSave() {
		when(repository.save(any(Users.class))).thenReturn(user);
		String registeredId = service.save(userCreate);
		Assertions.assertNotNull(registeredId);
		Assertions.assertEquals(expectedId, registeredId);
	}
	
	@Test
	@DisplayName("Test update Success")
	void testUpdate() {
		Optional<Users> existingUser = Optional.of(user);
		when(repository.findById(any(String.class))).thenReturn(existingUser);
		service.update(existingUser.get().getId(), predicates);
		Assertions.assertEquals(expectedId, existingUser.get().getId());
	}
	
	@Test
	@DisplayName("Test update Failed")
	void testUpdateShouldThrowNotFoundException() {
		Optional<Users> emptyResult = Optional.empty();
		when(repository.findById(any(String.class))).thenReturn(emptyResult);
		Exception exception = Assertions.assertThrows(UserNotFoundException.class,
				() -> service.update(user.getId(), predicates));
		Assertions.assertEquals(String.format("User with an Id of %s is not found", expectedId), exception.getMessage());
	}
	
	@Test
	@DisplayName("Test delete Success")
	void testDelete() {
		Optional<Users> existingUser = Optional.of(user);
		when(repository.findById(any(String.class))).thenReturn(existingUser);
		
		service.deleteUser(existingUser.get().getId());
		Assertions.assertEquals(expectedId, existingUser.get().getId());
	}
	
	@Test
	@DisplayName("Test delete Failed")
	void testDeleteShouldThrowNotFoundException() {
		Optional<Users> emptyResult = Optional.empty();
		when(repository.findById(any(String.class))).thenReturn(emptyResult);
		Exception exception = Assertions.assertThrows(UserNotFoundException.class,
				() -> service.deleteUser(user.getId()));
		Assertions.assertEquals(String.format("User with an Id of %s is not found", expectedId), exception.getMessage());
	}
	
}
