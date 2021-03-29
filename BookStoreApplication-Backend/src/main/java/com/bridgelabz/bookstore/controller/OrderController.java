package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IOrderServices;

import io.swagger.annotations.ApiOperation;
@RestController
@CrossOrigin
public class OrderController {
	@Autowired
	private IOrderServices orderService;
	
//	@Autowired
//	OrderServiceImp orderServiceimpl;
	
	@PostMapping("bookstore/placeOrder")
	public ResponseEntity<Response> placeOrder(@RequestHeader String token,@RequestParam Long bookId, @RequestParam Long addressId) throws Exception {
		Order orderdetails = orderService.placeOrder(token, bookId, addressId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(" CHECK YOUR MAIL  ORDER IS SUCCESSFULLY PLACED", 200, orderdetails));
	}
	
	@GetMapping(value = "bookstore/confirmBook/{bookId}")
	public ResponseEntity<Response> confirmBooktoOrder(@RequestHeader(name= "token") String token,@PathVariable("bookId") long bookId) throws Exception {	
		boolean userdetails = orderService.confirmBooktoOrder(token, bookId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("order is placed", 200, userdetails));
	
		
	}
	
	@GetMapping(value = "/books/{token}")
	public ResponseEntity<Response> getOrderlist(@PathVariable("token") String token) throws Exception {
		
		List<Order> orderdetails = orderService.getOrderList(token);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("placed orderlist", 200, orderdetails));
		
	}
	@GetMapping(value = "/books_count/{token}")
	public ResponseEntity<Response> getBooksCount(@PathVariable("token") String token) throws Exception {
		
		int userdetails = orderService.getCountOfBooks(token);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("count of books", 200, userdetails));
		
	}
	
	

	
	@ApiOperation(value = "Change Order Status by admin ")
	@PutMapping(value = "bookstore/orderStatusByAdmin")
	public ResponseEntity<Response> changeOrderStausByAdmin(@RequestParam String status,@RequestParam long orderId,@RequestHeader("token") String token) throws Exception {
		
		int orderStatusResult = orderService.changeOrderStatus(status,orderId);
		System.out.println("orderStatusResult :"+orderStatusResult);
			return ResponseEntity.status(HttpStatus.OK).body(new Response(orderId+" order status updated ",200,orderStatusResult));
		
	}
	
	
	@ApiOperation(value = "get allorder detrails for admin")
	@GetMapping(value = "bookstore/getOrdersByAdmin")
	public ResponseEntity<Response> getallOrders() throws Exception {
		
		List<Order> orderinfo = orderService.getallOrders();
		System.out.println("order ids: "+orderinfo);
			return ResponseEntity.status(HttpStatus.OK).body(new Response(" orders list ",200,orderinfo));
		
	}
	
	@ApiOperation(value = "get In progress order detrails for seller")
	@GetMapping(value = "bookstore/getOrdersByseller")
	public ResponseEntity<Response> getInProgressOrders() throws Exception {
		System.out.println("------------Seller order");
		List<Order> orderinfo = orderService.getInProgressOrders();
		System.out.println("seller  ------order ids: "+orderinfo);
			return ResponseEntity.status(200).body(new Response(" orders list ",200,orderinfo));
		
	}
}