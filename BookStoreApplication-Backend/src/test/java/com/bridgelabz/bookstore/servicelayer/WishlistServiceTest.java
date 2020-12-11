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

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.implementation.WishlistImplementation;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.JwtGenerator;

@RunWith(MockitoJUnitRunner.class)
public class WishlistServiceTest {
	
	@InjectMocks
	WishlistImplementation impl;
	
	@Mock
	JwtGenerator generate;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BookImple bookRepository;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test()
	public void getWishlistBooksTest() {
		
		Book book1 = new Book();
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		WishlistBook wishBook = new WishlistBook();
		wishBook.setWishlistId(1L);
		wishBook.setBooksList(bookList);
		
		List<WishlistBook> wishbookList = new ArrayList<WishlistBook>();
		wishbookList.add(wishBook);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setWishlistBook(wishbookList);		
		Optional<Users> userOptional = Optional.of(user);
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn( userOptional);
		
		List<WishlistBook> res = impl.getWishlistBooks("token");
		assertEquals(res , wishbookList);
		
	}
	
	@Test()
	public void removeWishlistBookTest() {
		
		Book book1 = new Book();
		book1.setBookId(1L);
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookId(2L);
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		WishlistBook wishBook = new WishlistBook();
		wishBook.setWishlistId(1L);
		wishBook.setBooksList(bookList);
		
		List<WishlistBook> wishbookList = new ArrayList<WishlistBook>();
		wishbookList.add(wishBook);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setWishlistBook(wishbookList);		
		Optional<Users> userOptional = Optional.of(user);
		Optional<Book> bookOptional = Optional.of(book1);
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn( userOptional);
		Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn( bookOptional);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn( user);
		
		boolean res = impl.removeWishBook("token", 1L);
		assertEquals(res , true);
		
	}
	
	@Test()
	public void getCountWishlistBookTest() {
		
		Book book1 = new Book();
		book1.setBookId(1L);
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookId(2L);
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		WishlistBook wishBook = new WishlistBook();
		wishBook.setWishlistId(1L);
		wishBook.setBooksList(bookList);
		
		List<WishlistBook> wishbookList = new ArrayList<WishlistBook>();
		wishbookList.add(wishBook);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setWishlistBook(wishbookList);		
		Optional<Users> userOptional = Optional.of(user);
		Optional<Book> bookOptional = Optional.of(book1);
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn( userOptional);
				
		int res = impl.getCountOfWishlist("token");
		assertEquals(res , 1);
		
	}
	
	@Test()
	public void addBookToWishlistTest() {
		
		Book book1 = new Book();
		book1.setBookId(1L);
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookId(2L);
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		WishlistBook wishBook = new WishlistBook();
		wishBook.setWishlistId(1L);
		wishBook.setBooksList(bookList);
		
		List<WishlistBook> wishbookList = new ArrayList<WishlistBook>();
		wishbookList.add(wishBook);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com"); 
		user.setUserId(1L);
		user.setWishlistBook(wishbookList);		
		Optional<Users> userOptional = Optional.of(user);
		Optional<Book> bookOptional = Optional.of(book1);
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn( userOptional);
		Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn( bookOptional);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn( user);
		
		boolean res = impl.removeWishBook("token", 1L);
		assertEquals(res , true);
		
	}

}
