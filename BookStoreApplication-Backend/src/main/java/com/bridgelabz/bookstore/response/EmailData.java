package com.bridgelabz.bookstore.response;
import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class EmailData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String subject;
	private String body;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}