package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.dto.RatingReviewDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.ReviewAndRating;
import com.bridgelabz.bookstore.response.BookResponse;
import com.bridgelabz.bookstore.service.IBookService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class BookStoreController {

	@Autowired
	IBookService bookservice;

	@PostMapping("books/{imageName}")
	public ResponseEntity<BookResponse> addBook(@PathVariable String imageName, @RequestBody BookDto information,@RequestHeader("token") String token) {
		
		boolean res=bookservice.addBooks(imageName,information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(200, "Book Added Successfully.."));
		else
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(400, "The Book details not added "));
	}
	


	@GetMapping("books/")
	public ResponseEntity<BookResponse> getBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getBookInfo(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("The Book details are", books));
	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByBooKName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */

	@GetMapping("books/approved")
	public ResponseEntity<BookResponse> getAllApproved(@RequestParam Optional<String> searchByBooKName,
			@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy,
			@RequestParam Optional<String> order) {
		Page<Book> books = bookservice.getBookAproval(searchByBooKName, page, sortBy, order);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Approved Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Approved Books available"));
	}
	
	@GetMapping(value = "books/getbook/{bookId}")
	public ResponseEntity<BookResponse> getBookbyId(@PathVariable("bookId") Long bookId) {
		Book info = bookservice.getBookbyId(bookId);
		return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("The book is", info));
	}

	
	@PutMapping("books/{bookId}")
	public ResponseEntity<BookResponse> editBook(@PathVariable("bookId") long bookId,@RequestBody EditBookDto information,@RequestHeader("token") String token){
		boolean res =bookservice.editBook(bookId,information,token);
		if(res)
			return ResponseEntity.status(HttpStatus.CREATED).body(new BookResponse(200, "The Book is Updated Successfully"));
		return null;
	}

	@DeleteMapping("books/{bookId}")
	public ResponseEntity<BookResponse> deleteBook(@PathVariable long bookId, @RequestHeader("token") String token) {
		boolean res = bookservice.deleteBook(bookId, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse(202, "The Book is Deleted"));
		return null;
	}

	@PutMapping("books/{bookId}/{status}")
	public ResponseEntity<BookResponse> editBookStatus(@PathVariable long bookId, @PathVariable String status,
			@RequestHeader("token") String token) {
		boolean res = bookservice.editBookStatus(bookId, status, token);
		if (res)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(202, "The Book Status is changed sucessfully.."));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "The Book Status is not updated.."));
	}



	@GetMapping("books/onHoldBooks")
	public ResponseEntity<BookResponse> getAllOnHoldBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllOnHoldBooks(token);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Approved & OnHold Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Approved & OnHold Books available"));
	}

	@GetMapping("books/rejectedBooks")
	public ResponseEntity<BookResponse> getAllRejectedBooks(@RequestHeader("token") String token) {
		List<Book> books = bookservice.getAllRejectedBooks(token);
		if (books != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse("The Rejected Book details are", books));
		else
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new BookResponse(400, "No Rejected Books available"));
	}
	@ApiOperation(value = "get all rating and reviews of the book ")
	@GetMapping("books/getratereviews")
	public ResponseEntity<BookResponse> getBookRatingAndReview(@RequestParam Long bookId){
		List<ReviewAndRating> rr = bookservice.getRatingsOfBook(bookId);
		if(rr != null)
		 return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BookResponse("Ratings and review", rr ));
		else
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookResponse("Ratings and review not found", rr ));	
	}
	
	@ApiOperation(value = "Get verified Books Count")
	@GetMapping("books/count")
	public ResponseEntity<BookResponse> getBooksCount(){
		int bookcount = bookservice.getBooksCount();
			 return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("Ratings and review", bookcount ));
			
	}
	
	@ApiOperation(value = "Write Review of the book")
	@PutMapping("books/ratingreview")
	public ResponseEntity<BookResponse> writeReview(@RequestBody RatingReviewDTO rrDto,@RequestHeader(name="token") String token, @RequestParam Long bookId){
		if(bookservice.writeReviewAndRating(token, rrDto , bookId))
		 return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("Thank you..for your review", 200 ));			
		else
			 return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new BookResponse("You are already given rate", 208 ));
			
	}
	
	@ApiOperation(value = "Average rating of the book")
	@GetMapping("books/avgrate")
	public ResponseEntity<BookResponse> avgRatingOfBook(@RequestParam long bookId){
		double rate = bookservice.avgRatingOfBook(bookId);
		if(rate>0.0)
		 return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("Avg rate", rate ));
		else

			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("Avg rate", 0 ));
				
	}
	
	@ApiOperation(value = "Books sorted by rating")
	@GetMapping("books/sortbyrate")
	public ResponseEntity<BookResponse> sortBookByRate(){
		List<Book> books = bookservice.sortBookByRate();
		if(books!=null)
		 return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("books fetched", books ));
		else
			return ResponseEntity.status(HttpStatus.OK).body(new BookResponse("books not fetched" , books));		
	}
	
	@PostMapping("books/bookimage/{bookId}")
	public ResponseEntity<BookResponse> uploadImage(@RequestParam("imageFile") MultipartFile file,@RequestHeader String token,@PathVariable long bookId)  {
		String imageName=file.getOriginalFilename();
	    boolean res=bookservice.uploadBookImage(bookId,imageName,token);
	     if(res)
	    	 return ResponseEntity.status(HttpStatus.OK).body(new BookResponse(202, "Image Uploaded Succesfully"));
	     else
	    	 return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new BookResponse(203,"error")); 
	}

	

}
