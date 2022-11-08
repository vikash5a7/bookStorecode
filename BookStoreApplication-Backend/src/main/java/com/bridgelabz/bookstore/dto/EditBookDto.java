package com.bridgelabz.bookstore.dto;

import java.time.LocalDateTime;

public class EditBookDto {
	private String bookName;
	private Long noOfBooks;
	private Double price;
	private String authorName;
	private String image;
	private String bookDetails;
	private LocalDateTime updatedAt;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Long getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(Long noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
