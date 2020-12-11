/**
 * 
 */
package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class UserDto {
	private String name;
	private String email;
	private String password;
	private Long mobileNumber;
	private String role;

}
