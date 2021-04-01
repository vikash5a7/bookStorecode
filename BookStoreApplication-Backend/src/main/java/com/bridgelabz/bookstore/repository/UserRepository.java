package com.bridgelabz.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long>{

//	@Query(value = "select * from user where user_id=?", nativeQuery = true)
//    String login();
//
////	@Query(value = "insert into user (user_id,user_name,user_email, password, create_time, user_number) values (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
////	User register(User user);
//
//	@Query(value = "update user set password=? where email=?", nativeQuery = true)
//	Users forgotPassword(String password, String email);
//
//	@Query(value = "select * from user where user_id=?", nativeQuery = true)
//	Optional<Users> findUserById(Long id);
//
//	@Query(value = "select * from user where email=?", nativeQuery = true)
//    Optional<Users> findUserByEmail(String email);
//
//	@Query(value = "update user set verify_status=true where user_id=?", nativeQuery = true)
//	boolean verify(long id);

//   @Query(value="delete from cart_details where cart_id=?;",nativeQuery=true)
//   CartDetails deleteCart(Long id);

}