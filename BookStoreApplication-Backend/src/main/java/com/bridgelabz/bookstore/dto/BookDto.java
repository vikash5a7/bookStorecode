package com.bridgelabz.bookstore.dto;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
@Component
public class BookDto {
	private String bookName;
	private Long noOfBooks;
	private Double price;
	private String authorName;
	private String image;
	private String bookDetails;
	
}