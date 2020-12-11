package com.bridgelabz.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.dto.AddressDto;

import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private long addressId;

	@Column(name = "customer_pincode")
	private String pincode;

	@Column(name = "customer_locality")
	private String locality;

	@Column(name = "customer_address")
	private String address;

	@Column(name = "customer_city")
	private String city;

	@Column(name = "customer_landmark")
	private String landmark;
	
	@Column(name = "country")
    private String country;
	
	@Column(name = "address_type")
    private String addressType;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String name;

	@Column
	private String state;
	public Address() {
		super();
	}

	public Address(AddressDto address2) {
	this.name=address2.getName();
	this.phoneNumber=address2.getPhoneNumber();
	this.landmark=address2.getLandmark();
	this.addressType=address2.getType();
	this.pincode=address2.getPincode();
	this.state=address2.getState();
	this.country=address2.getCountry();
	this.address=address2.getAddress();
	this.locality=address2.getLocality();
	}
	

}
