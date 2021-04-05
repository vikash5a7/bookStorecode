package com.bridgelabz.bookstore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.ReviewAndRating;
@Repository
public interface BookImple extends JpaRepository<Book, Long> {
	
	@Query("from Book where book_id=:id")
	Book fetchbyId(Long id);
	
	@Query( value = "select * from book", nativeQuery = true)
    List<Book> findAllPage(Pageable pageable);
	
	@Query("from Book where book_id=:id ")
	List<Book> fetchbyIdList(Long id);
	
	@Modifying
	@Query("delete from Book where book_id=:id")
	void deleteByBookId(long id);
	
	@Query( value = "select * from bookinfo where  status='approved'", nativeQuery = true)
    List<Book> getApprovedBooks();
	
	@Query( value = "select * from bookinfo where book_name like %?1% AND status='approved'", nativeQuery = true)
    Page<Book> findByBookName(String name, Pageable pageable);
	
	@Query( value = "select * from bookinfo where status='OnHold'", nativeQuery = true)
    List<Book> getAllonHoldBooks();
	
	@Query( value = "select rating and review from review_and_rating where book_id=:id", nativeQuery = true)
	List<ReviewAndRating> reviews(Long id);
	
	@Query( value = "select avg(rating) from review_and_rating where book_id=:id", nativeQuery = true)
    double avgRateOfBook(long id);

	@Modifying
	@Query("update from Book set status=:status where book_id=:id")
	int updateBookStatusByBookId(String status,long id);
	
	@Query( value = "select * from bookinfo where user_id=:id", nativeQuery = true)
    List<Book> getAllBooks(long id);
	
	@Query( value = "select * from bookinfo where status='approved'", nativeQuery = true)
    List<Book> getAllApprovedBooks();

	@Query( value = "select * from bookinfo where status='onhold'", nativeQuery = true)
	List<Book> getAllOnHoldBooks();
	
	@Query( value = "select * from bookinfo where status='rejected'", nativeQuery = true)
	List<Book> getAllRejectedBooks();

	@Query("from Book where book_name=:name")
	Book fetchbyBookName(String name);
}