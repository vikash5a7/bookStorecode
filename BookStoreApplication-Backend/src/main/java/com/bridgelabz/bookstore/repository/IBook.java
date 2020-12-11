package com.bridgelabz.bookstore.repository;

import java.util.List;

import com.bridgelabz.bookstore.entity.Book;

public interface IBook {

	Book save(Book bookinformation);

	List<Book> getUsers();

}
