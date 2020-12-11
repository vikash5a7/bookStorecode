package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
//	@Modifying
//	@Query("update from order_details set order_status=:status where order_id=:orderId")
//	String updateOrderStatus(String status,long orderId);

	
	@Modifying
	@Query("update Order set order_status=:status where order_id=:orderId")
	int OrderStatusdefault(String status,long orderId);
	
	

	@Query( value = "select * from order_details ", nativeQuery = true)
    List<Order> getorder();
	
	@Query( value = "select * from order_details where order_status='in progress'", nativeQuery = true)
    List<Order> getInProgressOrder();
	
	@Query( value = "select user_id from order_details where order_id=:orderId", nativeQuery = true)
    long findUserId(long orderId);
}
