package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;

public interface IAdminService {
	

	boolean verifyBook(long bookId, String staus, String token);


	List<Book> getBooksByStatus(String status);


}
