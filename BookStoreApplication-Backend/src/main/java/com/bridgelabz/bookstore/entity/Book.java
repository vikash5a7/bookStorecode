package com.bridgelabz.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookinfo")
public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	private Long userId;
	private String bookName;
	private Long noOfBooks;
	private Double price;
	private String authorName;
	@Column(columnDefinition="TEXT")
	private String bookDetails;
	private LocalDateTime createdDateAndTime;
	private LocalDateTime updatedDateAndTime;
	private String status;
	private String image;
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	public String getBookDetails() {
		return bookDetails;
	}
	public void setBookDetails(String bookDetails) {
		this.bookDetails = bookDetails;
	}
	public LocalDateTime getCreatedDateAndTime() {
		return createdDateAndTime;
	}
	public void setCreatedDateAndTime(LocalDateTime createdDateAndTime) {
		this.createdDateAndTime = createdDateAndTime;
	}
	public LocalDateTime getUpdatedDateAndTime() {
		return updatedDateAndTime;
	}
	public void setUpdatedDateAndTime(LocalDateTime updatedDateAndTime) {
		this.updatedDateAndTime = updatedDateAndTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}