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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IWishlistService;

@RestController

@CrossOrigin("*")
public class WishlistController {
	@Autowired
	private IWishlistService wishbookService;

	@PostMapping("bookstore/v3/wishlist/addbookWishlist/{bookId}")
	public ResponseEntity<Response> addBooksToWish(@RequestHeader String token, @PathVariable long bookId)
			throws Exception {
		List<WishlistBook> wishbook = wishbookService.addwishBook(token, bookId);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("book is added to wishlist Bag", 200, wishbook));
	}

	@GetMapping("bookstore/v3/wishlist/getwishbooks")
	public ResponseEntity<Response> getBooksfromWish(@RequestHeader(name = "token") String token) throws Exception {
		List<WishlistBook> wishbook = wishbookService.getWishlistBooks(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(" wishlist Bag is fetched", 200, wishbook));
	}

	@DeleteMapping("bookstore/v3/wishlist/removeWishlist/{bookId}")
	public ResponseEntity<Response> removeBooksToWish(@RequestHeader String token, @PathVariable Long bookId)
			throws Exception {
		boolean wishbook = wishbookService.removeWishBook(token, bookId);
		if (wishbook != false) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new Response("book romoved from wishlist Bag", 200, wishbook));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response("there no book found to remove", 200, wishbook));
	}

	@GetMapping("bookstore/v3/wishlist/wishlistcount")
	public ResponseEntity<Response> getWishBooksCount(@RequestHeader(name = "token") String token) throws Exception {
		int wishbookCount = wishbookService.getCountOfWishlist(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("no of wishbooks", 200, wishbookCount));
	}
}