package com.bridgelabz.bookstore.controllayer;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.AdminController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.service.IAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
class AdminControllerTest {
	
	@InjectMocks
	private AdminController controller;  

	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;

	@Mock
	private IAdminService adminService;
	
	private static final String GET_BOOKS_BY_STATUS_URI = "/admin/books";
	private static final String UPDATE_BOOK_STATUS = "/admin/update/{bookId}";
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	final void get_All_Books_Test_By_Status() throws Exception {
		Book book=new Book();
		
		book.setBookName("book1");
		book.setAuthorName("amit");
		book.setBookDetails("Some book");
		book.setImage("sita.jpg");
		book.setNoOfBooks(20L);
		book.setPrice(200.00);
		book.setStatus("OnHold");
		book.setCreatedDateAndTime(null);
		book.setUpdatedDateAndTime(null);
		
		
		Book book1=new Book();
		
		book.setBookName("book2");
		book.setAuthorName("sadhguru");
		book.setBookDetails("Some book1");
		book.setImage("death.jpg");
		book.setNoOfBooks(23L);
		book.setPrice(204.00);
		book.setStatus("OnHold");
		book.setCreatedDateAndTime(null);
		book.setUpdatedDateAndTime(null);
		
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book);
		bookList.add(book1);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_BOOKS_BY_STATUS_URI)
				.param("status", "OnHold")
				.contentType(MediaType.APPLICATION_JSON);
		
		Mockito.when(adminService.getBooksByStatus(Mockito.anyString())).thenReturn(bookList);
				
//		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
		
		ResultActions resultAction = mockMvc.perform(requestBuilder);
		
		MvcResult result = resultAction.andReturn();
		
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	}

	@Test
	final void update_Book_Status_Test() throws Exception {
		
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(UPDATE_BOOK_STATUS,1L)
				.param("status", "OnHold")
				.header("token", "validToken")
				.contentType(MediaType.APPLICATION_JSON);
		
		Mockito.when(adminService.verifyBook(Mockito.anyLong(),Mockito.anyString(),Mockito.anyString())).thenReturn(true);
				
//		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andReturn().getResponse();
		
		ResultActions resultAction = mockMvc.perform(requestBuilder);
		
		MvcResult result = resultAction.andReturn();
		System.out.println("check "+result.getResponse().getStatus());
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	}
	
	

}
