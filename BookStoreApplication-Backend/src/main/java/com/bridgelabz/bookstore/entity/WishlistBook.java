package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="wishbook")
public class WishlistBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long wishlistId;
    
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> booksList;
	

	private LocalDateTime wishlistTime;

	public long getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(long wishlistId) {
		this.wishlistId = wishlistId;
	}

	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}

	public LocalDateTime getWishlistTime() {
		return wishlistTime;
	}

	public void setWishlistTime(LocalDateTime wishlistTime) {
		this.wishlistTime = wishlistTime;
	}
}
