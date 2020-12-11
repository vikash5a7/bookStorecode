package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.UserServiceImplementation;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;
import com.bridgelabz.bookstore.response.EmailData;
import com.bridgelabz.bookstore.util.EmailProviderService;
import com.bridgelabz.bookstore.util.JwtGenerator;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


	@InjectMocks
	UserServiceImplementation implementation;
	
	@Mock
	IUserRepository repository;
	
	@Mock
	ModelMapper modelMapper;
	
	@Mock
	BCryptPasswordEncoder encryption;
	
	@Mock
	JwtGenerator generate;
	
	@Mock
	EmailProviderService em;
	
	@Mock
	EmailData emailData;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test()
	public void userRegisterTest()
	{
		
		Users user1 = new Users();
		user1.setEmail("nayan@gmail.com");
		
		UserDto dto = new UserDto();
		dto.setEmail("nayan@gmail.com");
		Mockito.when(repository.getUser(Mockito.anyString())).thenReturn(null);
		Mockito.when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(user1);
		Mockito.when(encryption.encode(Mockito.anyString())).thenReturn("encodedPassword");
		Mockito.when(repository.save(Mockito.any())).thenReturn(user1);
		Mockito.when(generate.jwtToken(1L)).thenReturn("token");
		Mockito.doNothing().when(em).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
		
		boolean value = implementation.register(dto);
		assertEquals(value, true);
	}
	
	@Test()
	public void userLoginTest()
	{
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
		user.setRole("user");
		user.setPassword("nayan@123");
		user.setVerified(true);
		
		LoginInformation info = new LoginInformation();
		info.setEmail("nayan@gmail.com");
		info.setRole("user");
		info.setPassword("nayan@123");
		
	
		Mockito.when(repository.getUser(Mockito.anyString())).thenReturn(user);
		Mockito.when(encryption.matches(user.getPassword(),info.getPassword())).thenReturn(true);
		implementation.verifyPassword(user, info);
		Users users = implementation.login(info);
		assertEquals(users, user);
	}
	
	@Test()
	public void userVerifyTest() throws Exception
	{

		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(repository.verify(6L)).thenReturn(true);
		boolean value = implementation.verify("token");
		assertEquals(value, true);
	}
	
	@Test()
	public void updatePasswordTest()
	{
		PasswordUpdate updateDto = new PasswordUpdate();
		updateDto.setEmail("nayan@gmail.com");
		updateDto.setNewPassword("nayan@123");
		updateDto.setConfirmPassword("nayan@123");
		
		Users user = new Users();
		user.setUserId(1L);
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(repository.getUser(updateDto.getEmail())).thenReturn(user);
		Mockito.when(encryption.encode(updateDto.getConfirmPassword())).thenReturn("encodedPassword");
		Mockito.when(repository.upDate(updateDto , user.getUserId())).thenReturn(true);
	
		boolean value = implementation.update(updateDto, "token");
		assertEquals(value, true);
	}
}
