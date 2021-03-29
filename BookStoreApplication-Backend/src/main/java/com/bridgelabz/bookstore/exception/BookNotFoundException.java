package com.bridgelabz.bookstore.exception;

public class BookNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
