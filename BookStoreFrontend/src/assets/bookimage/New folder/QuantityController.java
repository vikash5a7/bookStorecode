
  package com.bridgelabz.bookstore.controller;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.CrossOrigin; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestHeader; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;
  
  import com.bridgelabz.bookstore.implementation.QuantityServiceImpl; import
  com.bridgelabz.bookstore.response.BookResponse; import
  com.bridgelabz.bookstore.service.QuantityService;
  
  @RestController
  
  @CrossOrigin
  
  @RequestMapping("/books") public class QuantityController {
  
  
  @Autowired QuantityServiceImpl Quantityservice;
  
  @PostMapping("/addbooksquantity{bookId}") public ResponseEntity<BookResponse>
  addQuantity(@PathVariable( value = "bookId") long bookId,@RequestHeader int
  Quantity) { Quantityservice.addQuantity(bookId, Quantity);
  
  return ResponseEntity.status(HttpStatus.CREATED).body(new
  BookResponse("The Quantity details are added",bookId)); }
  
  
 
  }
  
 