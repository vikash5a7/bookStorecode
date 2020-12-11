package com.bridgelabz.bookstore.servicelayer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.BookStoreController;
import com.bridgelabz.bookstore.controller.CartController;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.AdminServiceImpl;
import com.bridgelabz.bookstore.implementation.BookServiceImplementation;
import com.bridgelabz.bookstore.implementation.CartServiceImplimentation;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.BookInterface;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.service.IAdminService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImpleTest {
	
	@InjectMocks
	BookStoreController bookStoreController;
	
	@InjectMocks
	CartController cartController;
	
	@Mock
	BookServiceImplementation bookServiceImplementation;
	
	@Mock
	JwtGenerator jwt;

	@Mock
	BookInterface bookRepo;
	
	@Mock
	CustomerRepository userRepo;
	
	@InjectMocks
	private AdminServiceImpl adminService;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adminService).build();
	}
	
	@Mock
	private BookImple repository;
	
	Book info;
	
	@Mock
	CartServiceImplimentation cartService;
	
//	@Test
//	final void bookInfoByIfTest() {
//		
//		info = new Book();
//		
//		info.setBookId(1L);
//		info.setNoOfBooks((long)118);
//		info.setPrice(670.0);
//		info.setAuthorName("Abbar");
//		info.setBookDetails("bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history");
//		info.setImage("Akbar.jpg");
//		
//		when(bookServiceImplementation.getBookbyId((long)1)).thenReturn(info);
//		Book bookinfo = bookServiceImplementation.getBookbyId((long) 1);
//		
//		System.out.println("Expected Author Name : "+bookinfo.getAuthorName());
//		System.out.println("Actual Author Name : "+info.getAuthorName());
//		assertEquals(info.getAuthorName(), bookinfo.getAuthorName());
//		
//		System.out.println("Expected BookDetails : "+info.getBookDetails());
//		System.out.println("Actual BookDetails : "+bookinfo.getBookDetails());
//		assertEquals(info.getBookDetails(), bookinfo.getBookDetails());
//		
//		assertEquals(info.getPrice(), bookinfo.getPrice());
//		
//		assertEquals(info.getNoOfBooks(), bookinfo.getNoOfBooks());
//		
//		assertEquals(info.getImage(), bookinfo.getImage());
//	}
//	
//	
//	CartItem cartItem;
//	
//	@Test
//	final void addBooktoCartTest() {
//		
//		cartItem = new CartItem();
//		cartItem.setCartId(((long)5));
//		cartItem.setBooksList((List<Book>) info);
//		
//		when(cartService.addBooktoCart("token", 5)).thenReturn((List<CartItem>) cartItem);
//		
////		cartItem = cartService.addBooktoCart("token", 5);
////		
////		assertEquals("equals ", expecteds, actuals);
//		
//		
//	}
//	
	
	
	
//	cartId": 4,
//    "booksList": [],
//    "quantityOfBook": [],
//    "createdTime": "2020-06-10T17:15:26"
//  },
//  {
//    "cartId": 5,
//    "booksList": [
//      {
//        "bookId": 1,
//        "bookName": "The Great Mughal Hardcover ",
//        "noOfBooks": 118,
//        "price": 670,
//        "authorName": "Abbar",
//        "bookDetails": "bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history",
//        "createdDateAndTime": "2020-06-07T15:47:53",
//        "updatedDateAndTime": null,
//        "status": "OnHold",
//        "image": "Akbar.jpg",
//        "reviewRating": []
//      }
//    ],
//    "quantityOfBook": [
//      {
//        "quantity_id": 5,
//        "quantityOfBook": 1,
//        "totalprice": 670
//      }
//    ],
//    "createdTime": "2020-06-12T03:59:27.557"
//  }
//],
	
	
	
	
//	
//	"bookId": 1,
//    "bookName": "The Great Mughal Hardcover ",
//    "noOfBooks": 118,
//    "price": 670,
//    "authorName": "Abbar",
//    "bookDetails": "bu’l Fath Jalal-ud-din Muhammad Akbar, the third Mughal emperor, is widely regarded as one of the greatest rulers in India’s history",
//    "createdDateAndTime": "2020-06-07T15:47:53",
//    "updatedDateAndTime": null,
//    "status": "OnHold",
//    "image": "Akbar.jpg",
//    "reviewRating": []
	
	
	@Test
	final void verify_Book_Test() {
		String token="validToken";
		long userId=1L;
		Mockito.when(jwt.parseJWT(token)).thenReturn(userId);
		Users user=new Users();
		user.setName("brijesh");
		user.setMobileNumber(7259866545L);
		user.setAddress(null);
		user.setCartBooks(null);
		user.setCreatedDate(null);
		user.setRole("admin");
		
		Mockito.when(userRepo.getCustomerDetailsbyId(userId)).thenReturn(user);
		
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
		
		Mockito.when(bookRepo.findByBookId(Mockito.anyLong())).thenReturn(book);
		Mockito.when(bookRepo.save(book)).thenReturn(book);
		
		
		boolean res=adminService.verifyBook(1L,"OnHold",token);
		assertTrue(res==true);
	}
	
	@Test
	final void get_Books_By_Valid_Status_Test() {
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
		
		List<Book> actualBookList = new ArrayList<Book>();
		actualBookList.add(book);
		
		Mockito.when(bookRepo.findByStatus("OnHold")).thenReturn(actualBookList);
		
		assertThat(adminService.getBooksByStatus("OnHold")).isEqualTo(actualBookList);

	}
	
	@Test
	final void get_Books_By_InValid_Status_Test() {
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
		
		List<Book> actualBookList = new ArrayList<Book>();
		actualBookList.add(book);
		
		Mockito.when(bookRepo.findByStatus("OnHold")).thenReturn(actualBookList);

		assertThat(adminService.getBooksByStatus(Mockito.anyString())).isNotEqualTo(actualBookList);
	}
}
