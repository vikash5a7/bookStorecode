package com.bridgelabz.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BookNotFoundException(String message) {
		this.message = message;

	}
}
