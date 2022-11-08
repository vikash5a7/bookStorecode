
package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.request.LoginInformation;
import com.bridgelabz.bookstore.request.PasswordUpdate;

public interface UserServices {

	Users login(LoginInformation information);
	boolean register(UserDto ionformation);
	boolean verify(String token) throws Exception;
	boolean isUserExist(String email);
	boolean update(PasswordUpdate information, String token);
	List<Users> getUsers();
	Users getSingleUser(String token);
}