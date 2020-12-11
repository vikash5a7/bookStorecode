package com.bridgelabz.bookstore.servicelayer;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.implementation.OrderServiceImp;
import com.bridgelabz.bookstore.repository.CustomerRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.service.IOrderServices;
import com.bridgelabz.bookstore.util.JwtGenerator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;



@RunWith(MockitoJUnitRunner.class)
class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImp orderService;
	
	
	private MockMvc mockMvc;
	
	@Mock
	CustomerRepository userRepo;
	
	@Mock
	JwtGenerator jwt;
	
	@Mock
	OrderRepository orderRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderService).build();
	}
	
	
	@Test
	void place_Order_Test() {
		String token="validToken";
		long userId=1L;
		Mockito.when(jwt.parseJWT(token)).thenReturn(userId);
	}
	
	
	@Mock
	Users user1;
	
	@Test
	void confirm_Book_Order_Test() {
		String token="validToken";
		long userId=1L;
		Mockito.when(jwt.parseJWT(token)).thenReturn(userId);
		
		
		Users user=new Users();
		user.setUserId(1L);
		user.setName("brijesh");
		user.setMobileNumber(7259866545L);
		user.setAddress(null);
		user.setCartBooks(null);
		user.setCreatedDate(null);
		user.setRole("admin");
		
		Optional<Users> userOptional = Optional.of(user);
		
		Mockito.when(userRepo.findById(userId)).thenReturn(userOptional);
		
		
		Order order=new Order();
		order.setOrderId(1L);
		order.setOrderStatus("pending");
		order.setBooksList(null);
		order.setAddressId(1L);
		order.setOrderPlacedTime(null);
		order.setQuantityOfBooks(null);
		order.setTotalPrice(400D);
		
		Order order1=new Order();
		order1.setOrderId(2L);
		order1.setOrderStatus("pending");
		order1.setBooksList(null);
		order1.setAddressId(2L);
		order1.setOrderPlacedTime(null);
		order1.setQuantityOfBooks(null);
		order1.setTotalPrice(500D);
		
		
		List<Order> actualorderList = new ArrayList<Order>();
		actualorderList.add(order);
		actualorderList.add(order1);
		
		Mockito.when(user1.getOrderBookDetails()).thenReturn(actualorderList);

		
	}
	
	@Test
	void get_Order_List_Test() {
		String token="validToken";
		long userId=1L;
		Mockito.when(jwt.parseJWT(token)).thenReturn(userId);
		
		
		Users user =  new Users();
		user.setUserId(userId);
		user.setName("brijesh");
		user.setMobileNumber(7259866545L);
		user.setAddress(null);
		user.setCartBooks(null);
		user.setCreatedDate(null);
		user.setRole("admin");
		
		
		
		Optional<Users> userOptional = Optional.of(user);
		
		Mockito.when(userRepo.findById(userId)).thenReturn(userOptional);
		
		Order order=new Order();
		order.setOrderId(1L);
		order.setOrderStatus("pending");
		order.setBooksList(null);
		order.setAddressId(1L);
		order.setOrderPlacedTime(null);
		order.setQuantityOfBooks(null);
		order.setTotalPrice(400D);
		
//		Order order1=new Order();
//		order1.setOrderId(2L);
//		order1.setOrderStatus("pending");
//		order1.setBooksList(null);
//		order1.setAddressId(2L);
//		order1.setOrderPlacedTime(null);
//		order1.setQuantityOfBooks(null);
//		order1.setTotalPrice(500D);
		
		
		List<Order> actualorderList = new ArrayList<Order>();
		actualorderList.add(order);
//		actualorderList.add(order1);
		
		user.setOrderBookDetails(actualorderList);
		
//		Mockito.when(user.getOrderBookDetails()).thenReturn(actualorderList);

		assertThat(orderService.getOrderList(token)).isEqualTo(actualorderList);
		
		
	}
	
	@Test
	void change_Order_Status_Valid_Test() {
		
		Mockito.when(orderRepository.OrderStatusdefault("pending",(long)1)).thenReturn(1);
		
		int changedOrderStatus = orderService.changeOrderStatus("pending",(long)1);
		System.out.println("val "+changedOrderStatus);
		
		assertThat(orderService.changeOrderStatus("pending", (long)1)).isEqualTo(1);
	}
	
	@Test
	void change_Order_Status_Invaid_Test() {
		
		Mockito.when(orderRepository.OrderStatusdefault("pending",(long)1)).thenReturn(1);
		
		int changedOrderStatus = orderService.changeOrderStatus("pending",(long)1);
		System.out.println("val "+changedOrderStatus);
		
		assertNotEquals( 2,changedOrderStatus);
	}
	
	@Test
	void get_All_Order_Details_Test() {
		
		Order order=new Order();
		order.setOrderId(1L);
		order.setOrderStatus("pending");
		order.setBooksList(null);
		order.setAddressId(1L);
		order.setOrderPlacedTime(null);
		order.setQuantityOfBooks(null);
		order.setTotalPrice(400D);
		
		Order order1=new Order();
		order1.setOrderId(2L);
		order1.setOrderStatus("pending");
		order1.setBooksList(null);
		order1.setAddressId(2L);
		order1.setOrderPlacedTime(null);
		order1.setQuantityOfBooks(null);
		order1.setTotalPrice(500D);
		
		
		List<Order> actualorderList = new ArrayList<Order>();
		actualorderList.add(order1);
		
		Mockito.when(orderRepository.getorder()).thenReturn(actualorderList);
		
		assertThat(orderService.getallOrders()).isEqualTo(actualorderList);
	}
	
	
}
