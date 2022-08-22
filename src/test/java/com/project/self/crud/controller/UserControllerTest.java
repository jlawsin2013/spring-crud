package com.project.self.crud.controller;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.self.crud.dto.UserCreateDto;
import com.project.self.crud.dto.UserRecordDto;
import com.project.self.crud.service.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@MockBean
	private UserServiceImpl service;
	
	@Autowired
	private MockMvc mockMvc;
	
	String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	UserRecordDto userRecord;
	UserCreateDto userCreate;
	
	@BeforeEach
	void init() {
		LocalDateTime now = LocalDateTime.now();
		userRecord = UserRecordDto.builder().id("1").fname("John").lname("Lawsin").address("PH").createdAt(now).modifiedAt(now).build();
		userCreate = UserCreateDto.builder().fname("John").lname("Lawsin").address("PH").build();
	}
	
	@Test
	@DisplayName("GET /api/v1/users")
	void testGetUsers() throws Exception {
		List<UserRecordDto> users = Arrays.asList(userRecord);
		when(service.getAllUsers()).thenReturn(users);
		
		mockMvc.perform(get("/users"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		// validate the returned fields
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is(userRecord.getId())))
        .andExpect(jsonPath("$[0].fname", is(userRecord.getFname())))
        .andExpect(jsonPath("$[0].lname", is(userRecord.getLname())))
        .andExpect(jsonPath("$[0].address", is(userRecord.getAddress())))
        .andExpect(jsonPath("$[0].createdAt", is(userRecord.getCreatedAt().format(DateTimeFormatter.ofPattern(dateTimeFormat)))))
        .andExpect(jsonPath("$[0].modifiedAt", is(userRecord.getModifiedAt().format(DateTimeFormatter.ofPattern(dateTimeFormat)))));
	}
	
	@Test
	@DisplayName("Get /api/v1/users/{id}")
	void testGetUsersById() throws Exception {
		when(service.getUsersById("1")).thenReturn(userRecord);
		
		mockMvc.perform(get("/users/{id}", "1"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		// validate the returned fields
        .andExpect(jsonPath("$.id", is(userRecord.getId())))
        .andExpect(jsonPath("$.fname", is(userRecord.getFname())))
        .andExpect(jsonPath("$.lname", is(userRecord.getLname())))
        .andExpect(jsonPath("$.address", is(userRecord.getAddress())))
        .andExpect(jsonPath("$.createdAt", is(userRecord.getCreatedAt().format(DateTimeFormatter.ofPattern(dateTimeFormat)))))
        .andExpect(jsonPath("$.modifiedAt", is(userRecord.getModifiedAt().format(DateTimeFormatter.ofPattern(dateTimeFormat)))));
	}
	
	@Test
	@DisplayName("POST /api/v1/users")
	void testSaveUser() throws Exception {
		when(service.save(userCreate)).thenReturn("1");
		
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userCreate)))
		.andExpect(status().isCreated())
		.andExpect(content().encoding("UTF-8"))
		.andExpect(content().contentType("text/plain;charset=UTF-8"))
		// validate the returned fields
		.andExpect(jsonPath("$", is("A new user is created successfully: 1")));
	}
}
