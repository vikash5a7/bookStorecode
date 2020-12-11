package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Address;
import com.bridgelabz.bookstore.entity.Users;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> 
{
	@Query(value = "select * from users where user_id=?", nativeQuery = true)
	Users findUserById(Long uId);
	
	
	
	@Query(value = "select * from Address where user_id=?", nativeQuery = true)
	List<Address> findAddressByUserId(Long addressId);
	
	
	
	@Query(value = "select * from Address where address_id=?", nativeQuery = true)
	Address findAddressById(Long id);
	
	
	@Query(value = "select * from Address where user_id=? and address_type =?", nativeQuery = true)
	Address findAddressBytext(Long id,String type);
}
