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
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.implementation.CartServiceImplimentation;
import com.bridgelabz.bookstore.implementation.WishlistImplementation;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.JwtGenerator;



@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
	
	@InjectMocks
	CartServiceImplimentation impl;
	
	@Mock
	JwtGenerator generate;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	BookImple bookRepository;
	
	@Mock
	QuantityRepository quantityRepository;
	
	@Mock
	WishlistImplementation wishService;
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test()
	public void getBooksFromCartTest() {
		
		Book book1 = new Book();
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setCartId(1L);
		cartItem1.setBooksList(bookList);
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setCartId(1L);
		cartItem2.setBooksList(bookList);

		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem1);
		cartList.add(cartItem2);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
		user.setUserId(1L);
		user.setRole("user");
		user.setPassword("nayan@123");
		user.setVerified(true);
		user.setCartBooks(cartList);
		Optional<Users> userOptional = Optional.of(user);
		
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		
		List<CartItem> item = impl.getBooksfromCart("token");
		assertEquals(item , cartList);
		
	}
	
	@Test()
	public void removeBooksFromCartTest() {
		
		Book book1 = new Book();
		book1.setBookId(1L);
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		Quantity quantity = new Quantity();
		quantity.setQuantity_id(1L);
		quantity.setQuantityOfBook(2L);
		Optional<Quantity> quantityOptional = Optional.of(quantity);
		
		List<Quantity> quantityList = new ArrayList<Quantity>();
		quantityList.add(quantity);
		
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setCartId(1L);
		cartItem1.setBooksList(bookList);
		cartItem1.setQuantityOfBook(quantityList);
		
		
		CartItem cartItem2 = new CartItem();
		cartItem2.setCartId(2L);
		cartItem2.setBooksList(bookList);

		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem1);
		cartList.add(cartItem2);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
		user.setUserId(1L);
		user.setRole("user");
		user.setPassword("nayan@123");
		user.setVerified(true);
		user.setCartBooks(cartList);
		Optional<Users> userOptional = Optional.of(user);
		
		Book book = new Book();
		book.setBookId(1L);
		book1.setBookName("Fly me");
		Optional<Book> bookOptional = Optional.of(book);
		
		
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(bookOptional);
		Mockito.when(quantityRepository.findById(Mockito.anyLong())).thenReturn(quantityOptional);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		boolean value = impl.removeBooksFromCart("token", 1L);
		assertEquals(value , true);
		
	}
	
	@Test()
	public void getCountOfBooksFromCartTest() {
		
		Book book1 = new Book();
		book1.setBookName("Fly me");
		
		Book book2 = new Book();
		book2.setBookName("Into the Water");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
		bookList.add(book2);
		
		
		CartItem cartItem1 = new CartItem();
		cartItem1.setCartId(1L);
		cartItem1.setBooksList(bookList);
		
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem1);

		
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
		user.setUserId(1L);
		user.setRole("user");
		user.setPassword("nayan@123");
		user.setVerified(true);
		user.setCartBooks(cartList);
		Optional<Users> userOptional = Optional.of(user);
		
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		
		int count = impl.getCountOfBooks("token");
		assertEquals(count , 1);
		
	}
	
	@Test()
	public void addBooksToCartTest() {
		
		Book book1 = new Book();
		book1.setBookId(3L);
		book1.setBookName("Fly me");
		
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(book1);
	
		CartItem cartItem1 = new CartItem();
		cartItem1.setCartId(1L);
		cartItem1.setBooksList(bookList);

		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList.add(cartItem1);
		
		WishlistBook wishBook = new WishlistBook();
		wishBook.setWishlistId(1L);
		wishBook.setBooksList(bookList);
		
		List<WishlistBook> wishbookList = new ArrayList<WishlistBook>();
		wishbookList.add(wishBook);
		
		Users user = new Users();
		user.setEmail("nayan@gmail.com");
		user.setUserId(1L);
		user.setRole("user");
		user.setPassword("nayan@123");
		user.setVerified(true);
		user.setCartBooks(cartList);
		user.setWishlistBook(wishbookList);
		Optional<Users> userOptional = Optional.of(user);
		
		Book book = new Book();
		book.setBookId(1L);
		book.setNoOfBooks(2L);
		book1.setBookName("Into the water");
		Optional<Book> bookOptional = Optional.of(book);
		
		
		Mockito.when(generate.parseJWT(Mockito.anyString())).thenReturn(1L);
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(userOptional);
		Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(bookOptional);
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
		
		impl.cartbooks(book, user);
		List<CartItem> cartItems = impl.addBooktoCart("token", 5L);
		assertEquals(cartItems , cartList);
		
	}

}
