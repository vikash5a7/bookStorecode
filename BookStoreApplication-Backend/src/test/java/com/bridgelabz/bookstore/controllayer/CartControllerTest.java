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

import com.bridgelabz.bookstore.controller.CartController;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.service.ICartService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	CartController controller;
	
	@Mock
	private ICartService service;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}
	
	@Test
	public void addBooksToCartTest() throws Exception {
		
		CartItem cart1 = new CartItem();
		cart1.setCartId(1L);
		cart1.setCreatedTime(null);
		
		CartItem cart2 = new CartItem();
		cart2.setCartId(2L);
		cart2.setCreatedTime(null);
		
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cart1);
		cartList.add(cart2);
		
		Mockito.when(service.addBooktoCart(Mockito.anyString() , Mockito.anyLong())).thenReturn(cartList);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/bookstore/v3/cart/addbookCart/1")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
		
		
	}
	
	@Test
	public void getBooksFromCartTest() throws Exception {
		
		CartItem cart1 = new CartItem();
		cart1.setCartId(1L);
		cart1.setCreatedTime(null);
		
		CartItem cart2 = new CartItem();
		cart2.setCartId(2L);
		cart2.setCreatedTime(null);
		
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cart1);
		cartList.add(cart2);
		
		Mockito.when(service.getBooksfromCart(Mockito.anyString())).thenReturn(cartList);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/v3/cart/getcartbooks")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}
	
	@Test
	public void removeBooksFromCartTest() throws Exception {
		
		Mockito.when(service.removeBooksFromCart(Mockito.anyString() , Mockito.anyLong())).thenReturn(true);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.delete("/bookstore/v3/cart/removeCartBooks/1")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
		
		
	}
	
	@Test
	public void getBooksCountTest() throws Exception {
		
		Mockito.when(service.getCountOfBooks(Mockito.anyString())).thenReturn(2);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/v3/cart/bookCount")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
			
	}
	
	
	@Test
	public void increaseBooksCountTest() throws Exception {
		
		CartItem cart = new CartItem();
		cart.setCartId(1L);
		cart.setCreatedTime(null);
		
		CartDto cartDto = new CartDto();
		cartDto.setQuantityId(1L);
		cartDto.setQuantityOfBook(2L);
		
		ObjectMapper object = new ObjectMapper();
		Mockito.when(service.IncreaseBooksQuantityInCart(Mockito.anyString(),Mockito.anyLong(),Mockito.any(cartDto.getClass()))).thenReturn(cart);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put("/bookstore/v3/cart/increasebooksquantity")
				.header("token", "valid")
				.param("bookId", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(cartDto)))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
		
	}
	
	
	@Test
	public void decreaseBooksCountTest() throws Exception {
		
		CartItem cart = new CartItem();
		cart.setCartId(1L);
		cart.setCreatedTime(null);
		
		CartDto cartDto = new CartDto();
		cartDto.setQuantityId(1L);
		cartDto.setQuantityOfBook(2L);
		ObjectMapper object = new ObjectMapper();
		
		Mockito.when(service.descreaseBooksQuantityInCart(Mockito.anyString(),Mockito.anyLong() , Mockito.any())).thenReturn(cart);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.put("/bookstore/v3/cart/decreaseQuantityPrice")
				.header("token", "valid")
				.param("bookId", "1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(object.writeValueAsString(cartDto)))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
	}

}
