package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.bookstore.entity.Book;

public interface BookInterface extends CrudRepository<Book, Long>{
	
	List<Book> findByStatus(String status);
	
	Book findByBookId(Long bookId);

}
