package com.bridgelabz.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Quantity;
@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Long>{

}