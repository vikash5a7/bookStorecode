package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bridgelabz.bookstore.controller.BookStoreController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

class BookStoreControllerTests {

	@InjectMocks
	BookStoreController controller;

	@Mock
	IBookService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	final void testGetBooks() throws Exception {
		BookDto dto = new BookDto();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(dto);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books").content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		 Assert.assertTrue(HttpStatus.ACCEPTED.value() == result.getResponse().getStatus());
	}

	@Test
	final void testGetBookById() throws Exception {
		Book book = new Book();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/"+3L).content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
        Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus());
	}
	
	@Test
	final void testGetBookPagewise() throws Exception { 
		Book book = new Book();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/pagewise/"+1L).content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
        Assert.assertTrue(HttpStatus.ACCEPTED.value() ==  result.getResponse().getStatus());	
	}
	
	@Test 
	final void testUnSorting() throws Exception {
		List<Book> book = new ArrayList<Book>();
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books/unsorting").content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		Assert.assertTrue(HttpStatus.OK.value() ==  result.getResponse().getStatus());
	}
	
	@Test
	final void testaddBooks() throws Exception {
		BookDto info = new BookDto();
		info.setAuthorName("Naveen");
		info.setBookDetails("Naveen");
		info.setBookName("Java");
		info.setImage("Birds");
		info.setPrice(1000.00);
		info.setNoOfBooks((long) 2);
		ObjectMapper object = new ObjectMapper();
		String bookdto = object.writeValueAsString(info);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/books/addbook", info).content(bookdto).contentType(MediaType.APPLICATION_JSON);
		ResultActions resultAction = mockMvc.perform(request);
		MvcResult result = resultAction.andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.CREATED.value());
	}
}
