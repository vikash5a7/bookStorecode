package com.bridgelabz.bookstore.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Users  {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long userId;
		private String name;
		private String email;
		private String password;
		private Long mobileNumber;
		private LocalDateTime createdDate;
		private boolean isVerified;
		private String role;
		
		@OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
		@JoinColumn(name = "userId")
		private List<Address> address;
		

		@OneToMany(cascade = CascadeType.ALL, targetEntity = CartItem.class)
		@JoinColumn(name = "userId")
		private List<CartItem> cartBooks;
		
		@OneToMany(cascade = CascadeType.ALL, targetEntity = WishlistBook.class)
		@JoinColumn(name = "userId")
		private List<WishlistBook> wishlistBook;
		@OneToMany(cascade = CascadeType.ALL, targetEntity = Order.class)
		@JoinColumn(name = "userId")
		private List<Order> orderBookDetails;


		@Override
		public String toString() {
			return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", mobileNumber=" + mobileNumber + ", createdDate=" + createdDate + ", isVerified=" + isVerified
					+ ", role=" + role + ", address=" + address + ", cartBooks=" + cartBooks + "]";
		}


		public long getUserId() {
			return userId;
		}


		public void setUserId(long userId) {
			this.userId = userId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public Long getMobileNumber() {
			return mobileNumber;
		}


		public void setMobileNumber(Long mobileNumber) {
			this.mobileNumber = mobileNumber;
		}


		public LocalDateTime getCreatedDate() {
			return createdDate;
		}


		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}


		public boolean isVerified() {
			return isVerified;
		}


		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}


		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


		public List<Address> getAddress() {
			return address;
		}


		public void setAddress(List<Address> address) {
			this.address = address;
		}


		public List<CartItem> getCartBooks() {
			return cartBooks;
		}


		public void setCartBooks(List<CartItem> cartBooks) {
			this.cartBooks = cartBooks;
		}


		public List<WishlistBook> getWishlistBook() {
			return wishlistBook;
		}


		public void setWishlistBook(List<WishlistBook> wishlistBook) {
			this.wishlistBook = wishlistBook;
		}


		public List<Order> getOrderBookDetails() {
			return orderBookDetails;
		}


		public void setOrderBookDetails(List<Order> orderBookDetails) {
			this.orderBookDetails = orderBookDetails;
		}
}
