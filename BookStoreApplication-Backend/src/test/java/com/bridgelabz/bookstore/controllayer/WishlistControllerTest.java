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

import com.bridgelabz.bookstore.controller.WishlistController;
import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.service.IWishlistService;


@RunWith(MockitoJUnitRunner.class)
public class WishlistControllerTest {

	
	private MockMvc mockMvc;
	
	@InjectMocks
	WishlistController controller;
	
	@Mock
	private IWishlistService service;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		
	}
	
	@Test
	public void addBooksToWishlistTest() throws Exception{
		
		WishlistBook book1 = new WishlistBook();
		book1.setWishlistId(1L);
		
		WishlistBook book2 = new WishlistBook();
		book2.setWishlistId(2L);
		
		List<WishlistBook> wishList = new ArrayList<WishlistBook>();
		wishList.add(book1);
		wishList.add(book2);
		
		Mockito.when(service.addwishBook(Mockito.anyString(),Mockito.anyLong())).thenReturn(wishList);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/bookstore/v3/wishlist/addbookWishlist/1")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
		
		
	}
	
	@Test
	public void getBooksFromWishlistTest() throws Exception{
		
		WishlistBook book1 = new WishlistBook();
		book1.setWishlistId(1L);
		
		WishlistBook book2 = new WishlistBook();
		book2.setWishlistId(2L);
		
		List<WishlistBook> wishList = new ArrayList<WishlistBook>();
		wishList.add(book1);
		wishList.add(book2);
		
		Mockito.when(service.getWishlistBooks(Mockito.anyString())).thenReturn(wishList);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/v3/wishlist/getwishbooks")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
		
		
		
	}
	
	@Test
	public void removeBooksFromWishlistTest() throws Exception{
		
		Mockito.when(service.removeWishBook(Mockito.anyString(),Mockito.anyLong())).thenReturn(true);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.delete("/bookstore/v3/wishlist/removeWishlist/1")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	}
	
	@Test
	public void getBooksCountFromWishlistTest() throws Exception{
		int count = 2;
		
		Mockito.when(service.getCountOfWishlist(Mockito.anyString())).thenReturn(count);
		
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/v3/wishlist/wishlistcount")
				.header("token", "valid")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());
	}
}
