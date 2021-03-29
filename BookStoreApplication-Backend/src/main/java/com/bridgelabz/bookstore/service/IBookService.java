package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.dto.RatingReviewDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.ReviewAndRating;

public interface IBookService {

	boolean addBooks(String imageName, BookDto information, String token);

	List<Book> getBookInfo(String token);

	List<Book> sortGetAllBooks();

	List<Book> sorting(boolean value);

	List<Book> findAllPageBySize(int pagenumber);

	Book getBookbyId(Long bookId);

	Book getTotalPriceofBook(Long bookId, long quantity);

	boolean editBook(long bookId, EditBookDto information, String token);

	boolean deleteBook(long bookId, String token);

	List<Book> getAllAprovedBook();

	boolean editBookStatus(long bookId, String status, String token);

	List<Book> getAllOnHoldBooks(String token);

	List<Book> getAllRejectedBooks(String token);

	boolean writeReviewAndRating(String token, RatingReviewDTO rrDTO, Long bookId);

	List<ReviewAndRating> getRatingsOfBook(Long bookId);

	Integer getBooksCount();

	double avgRatingOfBook(Long bookId);

	Page<Book> getBookAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy,
			Optional<String> order);

	boolean uploadBookImage(long bookId, String imageName, String token);

	List<Book> sortBookByRate();
}