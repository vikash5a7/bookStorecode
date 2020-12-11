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

import lombok.Data;



@Data
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

		
		
}
