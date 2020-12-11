package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.AddressController;
import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.UpdateAddressDto;
import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.service.IAdressService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	AddressController controller;
	
	@Mock
	private IAdressService service;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}
	
	@Test
	public void addAddressTest() throws Exception{
		AddressDto addressDto = new AddressDto();
		addressDto.setCity("Bangalore");
		addressDto.setCountry("India");
		addressDto.setState("Karnataka");
		addressDto.setType("home");
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setAddressType("home");
		address.setCountry("India");
		address.setState("Karnataka");
	
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.addAddress(Mockito.any(),Mockito.anyString())).thenReturn(address);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/address/add")
				.header("token", "valid")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(addressDto)))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}
	
	@Test
	public void updateAddressTest() throws Exception{
		UpdateAddressDto addressDto = new UpdateAddressDto();
		addressDto.setCity("Bangalore");
		addressDto.setCountry("India");
		addressDto.setState("Karnataka");
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setAddressType("home");
		address.setCountry("India");
		address.setState("Karnataka");
	
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.updateAddress(Mockito.any(),Mockito.anyString())).thenReturn(address);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put("/address/updateAddress")
				.header("token", "valid")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(addressDto)))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}
	
	@Test
	public void deleteAddressTest() throws Exception{
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
			
		Mockito.when(service.deleteAddress(Mockito.anyString(),Mockito.anyLong())).thenReturn(user);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.delete("/address/delete")
				.header("token", "valid")
				.param("addressId", "1")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}
	
	@Test
	public void getAddressByUserIdTest() throws Exception{
		
		Address address = new Address();
		address.setAddressId(1L);
		address.setAddressType("home");
		address.setCountry("India");
		address.setState("Karnataka");
		
		Address address1 = new Address();
		address1.setAddressId(2L);
		address1.setAddressType("office");
		address1.setCountry("India");
		address1.setState("Kerala");
		
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		addressList.add(address1);
			
		Mockito.when(service.getAddressByUserId(Mockito.anyString())).thenReturn(addressList);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/address/users")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}
}
