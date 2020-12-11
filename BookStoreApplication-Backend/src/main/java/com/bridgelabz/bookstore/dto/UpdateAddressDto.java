package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class UpdateAddressDto {
	
	private long addressId;

	private String address;
	private String locality;
	private String name;
	private String city;
	private String landmark;
	private String state;
	private String pincode;
	private String country;
	private String phoneNumber;
	private String addressType;
}
