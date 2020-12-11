package com.bridgelabz.bookstore.servicelayer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.UpdateAddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.AdressImpService;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.JwtGenerator;


@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

	@InjectMocks
	AdressImpService impl;

	@Mock
	UserRepository userRepo;
	
	@Mock
	JwtGenerator generate;
	
	@Mock
	AddressRepository addressRepository;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test()
	public void addAddressTest() {
		
		Address address = new Address();
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setLandmark("hsr");
		address.setLocality("btm");
		address.setAddressType("home");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setAddress(addressList);
		
		Optional<Users> userOptional = Optional.of(user);
		
		AddressDto dto = new AddressDto();
		dto.setCity("Kochin");
		dto.setCountry("India");
		dto.setLandmark("Ksd");
		dto.setLocality("Perla");
		dto.setType("office");
		
		Address add = new Address(dto);
		
		Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn( userOptional);

		Mockito.when(addressRepository.save(Mockito.any())).thenReturn(add);
		Address res = impl.addAddress(dto, "token");
		assertEquals(res , add);
	}
	
	@Test()
	public void updateAddressTest() {
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setLandmark("hsr");
		address.setLocality("btm");
		address.setAddressType("home");
		
		Optional<Address> addressOptional = Optional.of(address);
		
		Address address1 = new Address();
		address1.setAddressId(2L);
		address1.setCity("Kochin");
		address1.setCountry("India");
		address1.setLandmark("Ksd");
		address1.setLocality("Perla");
		address1.setAddressType("office");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		addressList.add(address1);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setAddress(addressList);
		
		Optional<Users> userOptional = Optional.of(user);
		
		UpdateAddressDto updateDto = new UpdateAddressDto();
		updateDto.setAddressId(1L);
		updateDto.setCity("Mangalore");
		updateDto.setCountry("India");
		updateDto.setLandmark("perla");
		updateDto.setLocality("ksd");
		updateDto.setAddressType("home");

		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn( userOptional);
		Mockito.when(addressRepository.findById(updateDto.getAddressId())).thenReturn( addressOptional);
		Mockito.when(addressRepository.save(Mockito.any())).thenReturn(address);
		Address res = impl.updateAddress(updateDto, "token");
		assertEquals(res , address);
	}
	
	@Test()
	public void getAddressListTest() {
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setLandmark("hsr");
		address.setLocality("btm");
		address.setAddressType("home");
		
			
		Address address1 = new Address();
		address1.setAddressId(2L);
		address1.setCity("Kochin");
		address1.setCountry("India");
		address1.setLandmark("Ksd");
		address1.setLocality("Perla");
		address1.setAddressType("office");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		addressList.add(address1);

		Mockito.when(addressRepository.findAll()).thenReturn( addressList);
		List<Address> res = impl.getAllAddress();
		assertEquals(res , addressList);
	}
	
	@Test()
	public void getAddressListByIdTest() {
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setLandmark("hsr");
		address.setLocality("btm");
		address.setAddressType("home");
		
			
		Address address1 = new Address();
		address1.setAddressId(2L);
		address1.setCity("Kochin");
		address1.setCountry("India");
		address1.setLandmark("Ksd");
		address1.setLocality("Perla");
		address1.setAddressType("office");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		addressList.add(address1);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setAddress(addressList);
		
		Optional<Users> userOptional = Optional.of(user);

		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn( userOptional);
		Mockito.when(addressRepository.findAddressByUserId(Mockito.anyLong())).thenReturn( addressList);
		List<Address> res = impl.getAddressByUserId("token");
		assertEquals(res , addressList);
	}
	
	@Test()
	public void getAddressTest() {
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setCity("Bangalore");
		address.setCountry("India");
		address.setLandmark("hsr");
		address.setLocality("btm");
		address.setAddressType("home");
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
				
		Optional<Users> userOptional = Optional.of(user);

		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepo.findById(Mockito.anyLong())).thenReturn( userOptional);
		Mockito.when(addressRepository.findAddressBytext(1L,"home")).thenReturn( address);
		Address res = impl.getAddress("home", "token");
		assertEquals(res , address);
	}

}
