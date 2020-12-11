
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

import lombok.Data;

@Entity
@Data
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



}