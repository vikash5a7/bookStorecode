
package com.bridgelabz.bookstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private Long orderId;
	@Column(name = "order_placed_time")
	private LocalDateTime orderPlacedTime;
	


	private String orderStatus;
	
	private Double totalPrice;
	
	private Long addressId;

	
	@OneToMany
	(cascade = CascadeType.ALL, targetEntity = Quantity.class)
	@JoinColumn(name = "orderId")
	private List<Quantity> QuantityOfBooks;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Book> BooksList;
	 public Order() {
		 
	 }
	

	public Order(Long orderId, LocalDateTime orderPlacedTime, List<Quantity> quantityOfBooks,
			List<Book> booksList) {
		super();
		this.orderId = orderId;
		this.orderPlacedTime = orderPlacedTime;
		QuantityOfBooks = quantityOfBooks;
		BooksList = booksList;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public LocalDateTime getOrderPlacedTime() {
		return orderPlacedTime;
	}


	public void setOrderPlacedTime(LocalDateTime orderPlacedTime) {
		this.orderPlacedTime = orderPlacedTime;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public List<Quantity> getQuantityOfBooks() {
		return QuantityOfBooks;
	}


	public void setQuantityOfBooks(List<Quantity> quantityOfBooks) {
		QuantityOfBooks = quantityOfBooks;
	}


	public List<Book> getBooksList() {
		return BooksList;
	}


	public void setBooksList(List<Book> booksList) {
		BooksList = booksList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}