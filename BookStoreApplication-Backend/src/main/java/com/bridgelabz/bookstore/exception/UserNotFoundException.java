/**
 * 
 */
package com.bridgelabz.bookstore.exception;

public class UserNotFoundException extends Exception {

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
	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}
}
