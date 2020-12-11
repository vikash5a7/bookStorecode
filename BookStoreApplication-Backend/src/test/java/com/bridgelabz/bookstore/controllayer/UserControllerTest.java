package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.UserController;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.request.PasswordReset;
import com.bridgelabz.bookstore.request.PasswordUpdate;
import com.bridgelabz.bookstore.service.UserServices;
import com.bridgelabz.bookstore.util.JwtGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {


	private MockMvc mockMvc;

	@InjectMocks
	UserController controller;

	
	@Mock
	private UserServices service;
	
	@Mock
	private JwtGenerator generate;


	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}

	@Test
	public void userRegistrationtest() throws Exception{


		UserDto userDto = new UserDto();
		userDto.setEmail("nayan12@gmail.com");
		userDto.setMobileNumber((long) 904820061);
		userDto.setName("nayan");
		userDto.setPassword("nayan@12");
		userDto.setRole("admin");
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.register(Mockito.any(UserDto.class))).thenReturn(true);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/registration")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(userDto))).andReturn();
		
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());

	}

	@Test
	public void userLogintest() throws Exception{
		Users user = new Users();
		user.setName("Nayan");
		user.setUserId(1);
		LoginInformation loginDto = new LoginInformation();
		loginDto.setEmail("nayangkumar@gmail.com");
		loginDto.setPassword("nayan@123");
		loginDto.setRole("admin");
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.login(Mockito.any())).thenReturn(user);
		Mockito.when(generate.jwtToken(Mockito.anyLong())).thenReturn("token");
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(loginDto))).andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}
	
	@Test
	public void userVerificationtest() throws Exception{
	
		Mockito.when(service.verify(Mockito.anyString())).thenReturn(true);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/user/verify/token")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}
	
	@Test
	public void forgotPasswordtest() throws Exception{
		PasswordReset user = new PasswordReset();
		user.setEmail("nayangkumar@gmail.com");
		
	
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.isUserExist(Mockito.anyString())).thenReturn(true);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/user/forgotpassword")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(user))).andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}
	
	@Test
	public void passwordUpdatetest() throws Exception{
		PasswordUpdate user = new PasswordUpdate();
		user.setEmail("nayangkumar@gmail.com");
		user.setNewPassword("nayan@123");
		user.setConfirmPassword("nayan@123");
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.update(Mockito.any(),Mockito.anyString())).thenReturn(true);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put("/user/update/token")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(user))).andReturn();
		System.out.println("register response:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	
	}
	
}
