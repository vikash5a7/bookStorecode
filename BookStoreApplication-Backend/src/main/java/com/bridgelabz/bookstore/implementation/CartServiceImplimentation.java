package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.CartItem;
import com.bridgelabz.bookstore.entity.Quantity;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.entity.WishlistBook;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.QuantityRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.ICartService;
import com.bridgelabz.bookstore.service.IWishlistService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class CartServiceImplimentation implements ICartService {
	@Autowired
	private JwtGenerator generate;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookImple bookRepository;
	@Autowired
	private IWishlistService wishService;
	@Autowired
	private QuantityRepository quantityRepository;
	Users user = new Users();

	@Transactional
	@Override
	public List<CartItem> addBooktoCart(String token, long bookId) {

		Long id;

		id = (long) generate.parseJWT(token);

		Users user = userRepository.findById(id).orElse(null);

		Book book = bookRepository.findById(bookId).get();
		if (book != null) {
			// if the book present in wishlist and book number is not equal to zero
			Long l = book.getNoOfBooks();
			int i = l.intValue();
			if (i > 0) {
				List<WishlistBook> wishbook = user.getWishlistBook();
				for (WishlistBook wishbooks : wishbook) {

					boolean b = wishbooks.getBooksList().contains(book);
					if (b == true) {
						wishService.removeWishBook(token, book.getBookId());
					}

				} // if book present in the wishbook

				List<Book> books = null;
				for (CartItem d : user.getCartBooks()) {
					books = d.getBooksList();
				}

				if (books == null) {
					Users userdetails = this.cartbooks(book, user);
					return userRepository.save(userdetails).getCartBooks();
				}
				/**
				 * Checking whether book is already present r not
				 */

				Optional<Book> cartbook = books.stream().filter(t -> t.getBookId() == bookId).findFirst();

				if (cartbook.isPresent()) {
					throw null;
				} else {
					Users userdetails = this.cartbooks(book, user);
					return userRepository.save(userdetails).getCartBooks();
				}

			} // i==0

			throw new UserException("Out of stock u cannot add to cart");
		} // book
		return null;

	}

	public Users cartbooks(Book book, Users user) {
		long quantity = 1;
		CartItem cart = new CartItem();
		Quantity qunatityofbook = new Quantity();
		ArrayList<Book> booklist = new ArrayList<>();
		booklist.add(book);
		cart.setCreatedTime(LocalDateTime.now());
		cart.setBooksList(booklist);
		ArrayList<Quantity> quantitydetails = new ArrayList<Quantity>();
		qunatityofbook.setQuantityOfBook(quantity);

		qunatityofbook.setTotalprice(book.getPrice());

		quantitydetails.add(qunatityofbook);
		cart.setQuantityOfBook(quantitydetails);
		user.getCartBooks().add(cart);
		return user;
	}

	@Transactional
	@Override
	public List<CartItem> getBooksfromCart(String token) {
		Long id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<CartItem> cartItem = new ArrayList<>();
			for (CartItem cartBooks : user.getCartBooks()) {
				if (!(cartBooks.getBooksList().isEmpty())) {
					cartItem.add(cartBooks);
				}
			}
			return cartItem;
		} // user.

		return null;
	}

	@Transactional
	@Override
	public boolean removeBooksFromCart(String token, Long bookId) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Book book = bookRepository.findById(bookId).get();
			if (book != null) {
				Quantity quantity = quantityRepository.findById(id).orElseThrow(null);
				for (CartItem cartt : user.getCartBooks()) {
					boolean exitsBookInCart = cartt.getBooksList().stream()
							.noneMatch(books -> books.getBookId().equals(bookId));
					if (!exitsBookInCart) {
						userRepository.save(user);
						cartt.getQuantityOfBook().remove(quantity);
						cartt.getBooksList().remove(book);
						cartt.getQuantityOfBook().clear();
						boolean users = userRepository.save(user).getCartBooks() != null ? true : false;
						if (user != null) {
							return users;
						}
					}

				}
			} // book
				// .book....exception here....
		} // user
		return false;

	}

	@Transactional
	@Override
	public int getCountOfBooks(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		int countOfBooks = 0;
		Users user = userRepository.findById(id).get();
		if (user != null) {
			List<CartItem> cartBooks = user.getCartBooks();
			for (CartItem cart : cartBooks) {
				if (!cart.getBooksList().isEmpty()) {
					countOfBooks++;
				}
			}
			return countOfBooks;
		} // user
			// ....write userwxception

		return 0;
	}

	@Transactional
	@Override
	public CartItem IncreaseBooksQuantityInCart(String token, Long bookId, CartDto bookQuantityDetails) {
		Long id;
		id = (long) generate.parseJWT(token);

		Long quantityId = bookQuantityDetails.getQuantityId();
		Long quantity = bookQuantityDetails.getQuantityOfBook();
		Users user = userRepository.findById(id).get();
		if (user != null) {
			Book book = bookRepository.findById(bookId).get();
			if (book != null) {

				double totalprice = book.getPrice() * (quantity + 1);
				boolean notExist = false;
				for (CartItem cartt : user.getCartBooks()) {
					if (!cartt.getBooksList().isEmpty()) {
						notExist = cartt.getBooksList().stream().noneMatch(books -> books.getBookId().equals(bookId));

						if (!notExist) {

//						

							Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
							quantityDetails.setQuantityOfBook(quantity + 1);
							quantityDetails.setTotalprice(totalprice);
							if (quantityDetails.getQuantityOfBook() <= book.getNoOfBooks()) {
								quantityRepository.save(quantityDetails);
								return cartt;
							}
							throw new UserException("there is no enough quantity of book");

						}

					} // cart
				}

			} // book

		} // user

		return null;
	}

	@Transactional
	@Override
	public CartItem descreaseBooksQuantityInCart(String token, Long bookId, CartDto bookQuantityDetails) {
		Long id;

		id = (long) generate.parseJWT(token);
		Long quantityId = bookQuantityDetails.getQuantityId();
		Long quantity = bookQuantityDetails.getQuantityOfBook();

		Users user = userRepository.findById(id).get();
		if (user != null) {
			Book book = bookRepository.findById(bookId).get();
			if (book != null) {
				double totalprice = book.getPrice() * (quantity - 1);
				boolean notExist = false;
				for (CartItem cartt : user.getCartBooks()) {
					if (!cartt.getBooksList().isEmpty()) {
						notExist = cartt.getBooksList().stream().noneMatch(books -> books.getBookId().equals(bookId));
						if (!notExist) {

							Quantity quantityDetails = quantityRepository.findById(quantityId).orElseThrow(null);
							quantityDetails.setQuantityOfBook(quantity - 1);
							quantityDetails.setTotalprice(totalprice);
							Long l = quantityDetails.getQuantityOfBook();
							int i = l.intValue();
							if (i >= 0) {
								if (i == 0) {
									removeBooksFromCart(token, bookId);
								}
								quantityRepository.save(quantityDetails);
								return cartt;
							}
							throw new UserException("invalid Quantity");
						}

					}
				}
			} // book

		} // user
		return null;

	}

}