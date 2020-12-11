package com.bridgelabz.bookstore.response;

import org.springframework.http.HttpStatus;

public class UserResponse {

	private String Message;
	private HttpStatus status;
	private Object obj;
	

	public UserResponse(String message, Object obj,HttpStatus status) {
		super();
		this.status = status;
		Message = message;
		this.obj = obj;
	}


	public String getMessage() {
		return Message;
	}


	public void setMessage(String message) {
		Message = message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public Object getObj() {
		return obj;
	}


	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
