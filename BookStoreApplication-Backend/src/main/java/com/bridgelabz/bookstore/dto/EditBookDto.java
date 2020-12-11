package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Data;



@Data
@Component
public class EditBookDto {
	private String bookName;
	private Long noOfBooks;
	private Double price;
	private String authorName;
	private String image;
	private String bookDetails;
	private LocalDateTime updatedAt;
}
