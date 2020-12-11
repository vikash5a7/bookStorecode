package com.bridgelabz.bookstore.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "customerInfo")
public class CustomerInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String name;
	private long email;
	private String password;
	private long phoneNumber;
	private boolean isVerified;
	private boolean isAdmin;
	private boolean isSeller;                                         
	@OneToMany
	private List<Address> addresses;

}