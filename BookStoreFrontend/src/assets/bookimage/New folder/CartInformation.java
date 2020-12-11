package com.bridgelabz.bookstore.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "cartinfo")
public class CartInformation  {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)	
private long cartId;
@OneToOne
private CustomerInformation userId;
@ManyToMany
@JsonIgnore
private List<BookInformation> bookId;





}