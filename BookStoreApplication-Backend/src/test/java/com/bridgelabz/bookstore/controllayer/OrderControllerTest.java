package com.bridgelabz.bookstore.controllayer;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bridgelabz.bookstore.controller.OrderController;
import com.bridgelabz.bookstore.entity.Order;
import com.bridgelabz.bookstore.service.IOrderServices;



@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	OrderController controller;


	@Mock
	private IOrderServices service;

	private static final String GET_ORDERS_URI="/bookstore/getOrdersByAdmin";
	
	private static final String CHANGE_ORDER_STATUS_URI="/bookstore/orderStatusByAdmin";
	
	@BeforeEach
	void setUp(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}

	@Test
	public void placeOrderTest() throws Exception {
		Order order = new Order();
		order.setAddressId(1L);
		order.setOrderStatus("OnHold");
		order.setTotalPrice(1200D);
		Mockito.when(service.placeOrder(Mockito.anyString(),Mockito.anyLong(),Mockito.anyLong())).thenReturn(order);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/bookstore/placeOrder")
				.header("token", "valid")
				.param("bookId", "1")
				.param("addressId", "2")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());

	}

	@Test
	public void getOrderListTest() throws Exception{

		Order order1 = new Order();
		order1.setAddressId(1L);
		order1.setOrderStatus("OnHold");
		order1.setTotalPrice(1200D);

		Order order2 = new Order();
		order2.setAddressId(2L);
		order2.setOrderStatus("OnHold");
		order2.setTotalPrice(2200D);

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order1);
		orderList.add(order2);

		Mockito.when(service.getOrderList(Mockito.anyString())).thenReturn(orderList);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/books/token")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());


	}

	@Test
	public void getBooksCountTest() throws Exception {
		int count =2;
		Mockito.when(service.getCountOfBooks(Mockito.anyString())).thenReturn(count);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/books_count/token")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();

		assertEquals(result.getResponse().getStatus(), HttpStatus.ACCEPTED.value());

	}
	
	@Test
	public void getInProgressOrderTest() throws Exception{

		Order order1 = new Order();
		order1.setAddressId(1L);
		order1.setOrderStatus("inprogress");
		order1.setTotalPrice(1200D);

		Order order2 = new Order();
		order2.setAddressId(2L);
		order2.setOrderStatus("inprogress");
		order2.setTotalPrice(2200D);

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order1);
		orderList.add(order2);

		Mockito.when(service.getInProgressOrders()).thenReturn(orderList);
		MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/getOrdersByseller")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		System.out.println("result:"+result.getResponse().getStatus());
		assertEquals(result.getResponse().getStatus(), 200);


	}
	
	@Test
	public void get_Order_Details_Test() throws Exception{

		Order order1 = new Order();
		order1.setOrderId(1234L);
		order1.setAddressId(1L);
		order1.setOrderStatus("pending");
		order1.setTotalPrice(1000D);

		Order order2 = new Order();
		order2.setOrderId(5678L);
		order2.setAddressId(2L);
		order2.setOrderStatus("pending");
		order2.setTotalPrice(500D);

		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order1);
		orderList.add(order2);

		Mockito.when(service.getallOrders()).thenReturn(orderList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(GET_ORDERS_URI)
				.contentType(MediaType.APPLICATION_JSON);
	

		ResultActions resultAction = mockMvc.perform(requestBuilder);
		
		MvcResult result = resultAction.andReturn();
		System.out.println("check "+result.getResponse().getStatus());
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	}
	
	@Test
	public void change_Order_Status_Test() throws Exception{

		Mockito.when(service.changeOrderStatus(Mockito.anyString(),Mockito.anyLong())).thenReturn(1);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(CHANGE_ORDER_STATUS_URI)
				.param("status", "in progress")
				.param("orderId","12345" )
				.header("token", "validToken")
				.contentType(MediaType.APPLICATION_JSON);
	

		ResultActions resultAction = mockMvc.perform(requestBuilder);
		
		MvcResult result = resultAction.andReturn();
		System.out.println("check "+result.getResponse().getStatus());
		Assert.assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
	}
	

}
