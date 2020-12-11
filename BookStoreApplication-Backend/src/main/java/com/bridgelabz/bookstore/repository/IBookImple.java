package com.bridgelabz.bookstore.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstore.entity.Book;

@Repository
public class IBookImple implements IBook {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Book save(Book bookinformation) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(bookinformation);
		return bookinformation;
	}

	@Override
	public List<Book> getUsers() {
		Session currentSession = entityManager.unwrap(Session.class);
		List BookList = currentSession.createQuery("from Book").getResultList();
		return BookList;
	}
}
