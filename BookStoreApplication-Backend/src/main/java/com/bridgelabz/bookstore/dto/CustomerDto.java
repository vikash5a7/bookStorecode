package com.bridgelabz.bookstore.dto;

import org.springframework.stereotype.Component;

import com.bridgelabz.bookstore.entity.Address;

@Component
public class CustomerDto {
	
	private String Name;
	private long Phonenumber;
	private Address Home;
	private Address Work;
	private Address Other;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getPhonenumber() {
		return Phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		Phonenumber = phonenumber;
	}
	public Address getHome() {
		return Home;
	}
	public void setHome(Address home) {
		Home = home;
	}
	public Address getWork() {
		return Work;
	}
	public void setWork(Address work) {
		Work = work;
	}
	public Address getOther() {
		return Other;
	}
	public void setOther(Address other) {
		Other = other;
	}
}
