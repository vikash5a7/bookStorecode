package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.ICartService;

@RestController
@CrossOrigin
public class CartController {

	@Autowired
	private ICartService cartService;
	@PostMapping("bookstore/v3/cart/addbookCart/{bookId}")
	public ResponseEntity<Response> addBooksToCart(@RequestHeader String token,@PathVariable long bookId) throws Exception {
	    List<CartItem> cartItem = cartService.addBooktoCart(token,bookId);
	    return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("book added to cart", 200,cartItem));
	  	}
	

	@GetMapping("bookstore/v3/cart/getcartbooks")
	public ResponseEntity<Response> getBooksfromCart(@RequestHeader(name="token")  String token) throws Exception {
		    List<CartItem> cartdetails = cartService.getBooksfromCart(token);
		    return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("books fetched from cart", 200,cartdetails));
	}

	
	@DeleteMapping("bookstore/v3/cart/removeCartBooks/{bookId}")
	public ResponseEntity<Response> removeBooksToCart(@RequestHeader(name="token") String token ,@PathVariable Long bookId) throws Exception {
		System.out.println("jjjjjjjjjjj"+ token + bookId);
		boolean cartdetails = cartService.removeBooksFromCart(token,bookId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("book removed from cart", 200,cartdetails));  				
	}
	
	@GetMapping("bookstore/v3/cart/bookCount")
	public ResponseEntity<Response> getBooksCount(@RequestHeader(name="token") String token) throws Exception {
		    int cartdetails = cartService.getCountOfBooks(token);
		    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Count of to book in cart", 200,cartdetails));
	}
	
	@PutMapping("bookstore/v3/cart/increasebooksquantity")
	public ResponseEntity<Response> increaseBooksQuantity(@RequestHeader String token,@RequestParam Long bookId,
			@RequestBody CartDto bookQuantityDetails) throws Exception {
		CartItem cartdetails = cartService.IncreaseBooksQuantityInCart(token, bookId,bookQuantityDetails);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("increased 1 quantity of book ", 200,cartdetails));   	
		
	}
	
	@PutMapping("bookstore/v3/cart/decreaseQuantityPrice")
	public ResponseEntity<Response> descreaseBooksQuantity(@RequestHeader(name="token") String token,@RequestParam("bookId") Long bookId,@RequestBody CartDto bookQuantityDetails) throws Exception {
		CartItem cartdetails = cartService.descreaseBooksQuantityInCart(token, bookId, bookQuantityDetails);
		  return ResponseEntity.status(HttpStatus.ACCEPTED).body
				  (new Response("decreased 1 quantity of book ", 200,cartdetails));	
 }
}
