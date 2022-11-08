package com.bridgelabz.bookstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstore.dto.AddressDto;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
