package com.bridgelabz.bookstore.implementation;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.EditBookDto;
import com.bridgelabz.bookstore.dto.RatingReviewDTO;
import com.bridgelabz.bookstore.entity.Book;
import com.bridgelabz.bookstore.entity.ReviewAndRating;
import com.bridgelabz.bookstore.entity.Users;
import com.bridgelabz.bookstore.exception.BookAlreadyExist;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookImple;
import com.bridgelabz.bookstore.repository.IUserRepository;
import com.bridgelabz.bookstore.repository.ReviewRatingRepository;
import com.bridgelabz.bookstore.response.EmailData;
import com.bridgelabz.bookstore.service.IBookService;
import com.bridgelabz.bookstore.util.EmailProviderService;
import com.bridgelabz.bookstore.util.JwtGenerator;

@Service
public class BookServiceImplementation implements IBookService {
	private Book bookinformation = new Book();
	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private EmailProviderService em;
	@Autowired
	private EmailData emailData;

	@Autowired
	private BookImple repository;

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	AddressRepository addrepository;

	@Autowired
	private JwtGenerator generate;

	
	@Autowired
	private ReviewRatingRepository rrRepository;

    @Autowired
    private  WishlistImplementation WishServiceNotify;
	
	@Transactional
	@Override

	public boolean addBooks(String imageName,BookDto information,String token)
	{	
		Long id;
	
			id = (long) generate.parseJWT(token);
			Users userInfo = userRepository.getUserById(id);
			if(userInfo != null) 
			{			
				String userRole = userInfo.getRole();
				System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;
				if (fetchRole.equals("seller") ) 
				{
					Book book=repository.fetchbyBookName(information.getBookName());
					System.out.println("Book name "+information.getBookName());
					if(book ==null)
					{
						bookinformation = modelMapper.map(information, Book.class);
						bookinformation.setBookName(information.getBookName());
						bookinformation.setAuthorName(information.getAuthorName());
						bookinformation.setPrice(information.getPrice());  
						bookinformation.setImage(imageName);
						bookinformation.setStatus("OnHold");
						bookinformation.setNoOfBooks(information.getNoOfBooks());
						bookinformation.setCreatedDateAndTime(LocalDateTime.now());
						bookinformation.setUserId(id);
						repository.save(bookinformation);
						return true;
					}
					else
					{
						throw new BookAlreadyExist("Book is already exist Exception..");
					}
				}
				else 
				{
					throw new UserException("Your are not Authorized User");
				}
			
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	@Transactional
	@Override
	public List<Book> getBookInfo(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> books = repository.getAllBooks(id);
			return books;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	public double getOriginalPrice(double price, long quantity) {
		long result = (long) (price / quantity);
		return result;
	}

	@Override
	public Book getTotalPriceofBook(Long bookId, long quantity) {
		Book bookinfo = repository.fetchbyId(bookId);
		double Price = bookinfo.getPrice();

		long Quantity = quantity;

		if (Quantity <= bookinfo.getNoOfBooks() || Quantity >= bookinfo.getNoOfBooks()) {
			if (bookinfo != null && quantity > 0) {
				double price = getOriginalPrice(Price, bookinfo.getNoOfBooks());
				double totalPrice = (price * Quantity);
				bookinfo.setNoOfBooks(quantity);

				bookinfo.setNoOfBooks(quantity);

				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			} else if (bookinfo != null && quantity < 1) {
				double price = getOriginalPrice(Price, bookinfo.getNoOfBooks());
				double totalPrice = (price * 1);
				bookinfo.setNoOfBooks(quantity);
				bookinfo.setPrice(totalPrice);
				repository.save(bookinfo);
				return bookinfo;
			}
		}
		return null;
	}

	@Transactional
	@Override
	public List<Book> sortGetAllBooks() {
		List<Book> list = repository.findAll();
		list.sort((Book book1, Book book2) -> book1.getCreatedDateAndTime().compareTo(book2.getCreatedDateAndTime()));
		return list;
	}

	@Override
	public List<Book> sorting(boolean value) {
		List<Book> list = repository.findAll();
		if (value == true) {
			list.sort((Book book1, Book book2) -> book1.getPrice().compareTo(book2.getPrice()));
			return list;
		} else {
			list.sort((Book book1, Book book2) -> book1.getPrice().compareTo(book2.getPrice()));
			Collections.reverse(list);
			return list;
		}
	}

	@Override
	public List<Book> findAllPageBySize(int pagenumber) {
		long count = repository.count();
		int pageSize = 2;
		int pages = (int) ((count / pageSize));
		int i = pagenumber; // i should start with zero or 0...
		while (i <= pages) {
			List<Book> list = repository.findAllPage(PageRequest.of(i, pageSize));
			i++;
			return list;
		}
		return null;
	}

	@Override
	public Book getBookbyId(Long bookId) {
		Book info = repository.fetchbyId(bookId);
		if (info != null) {
			return info;
		}
		return null;
	}

	@Override

	public boolean editBook(long bookId,EditBookDto information,String token) {
		
		Long id;
	
			id = (long) generate.parseJWT(token);
			Users userInfo = userRepository.getUserById(id);
			if(userInfo != null) 
			{			
				String userRole = userInfo.getRole();
				System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;

				if (fetchRole.equals("seller") ) 
				{
					Book info =repository.fetchbyId(bookId);
					if(info!=null) 
					{
						Long l=info.getNoOfBooks();
						int beforeNoOfbooks=l.intValue();
						info.setBookId(bookId);
						info.setBookName(information.getBookName());
						info.setNoOfBooks(information.getNoOfBooks());
						info.setPrice(information.getPrice());
						info.setAuthorName(information.getAuthorName());
						info.setBookDetails(information.getBookDetails());
//						info.setImage(imageName);
						info.setUpdatedDateAndTime(information.getUpdatedAt());
					
						Long af=info.getNoOfBooks();
						int afterNoOfbooks=af.intValue();
				
//						if(after==before) {
//						
//						}
						
//						for (WishlistBook w : userInfo.getWishlistBook()) {
//							for(Book wishbook :w.getBooksList()) {
//						
//							if(wishbook.getBookId()==bookId) {
						if(beforeNoOfbooks==0) {			
							if(afterNoOfbooks>beforeNoOfbooks) {
								WishServiceNotify.setNotifyWishbooks(true);
								if(WishServiceNotify.isNotifyWishbooks()==true) {
//									Users userdetails=new Users();
			
//									emailData.setEmail(userdetails.getEmail());
									
									String body="<html> \n"
							 				
								 			
	 				+"<h3 ; style=\"background-color:#990000;color:#ffffff;\" >\n "
	 				+ "<center>Bookstore Notification</center> "
	 				+ "</h3>\n "
	 				+ "<body  style=\"background-color:#FAF3F1;\">\n"+
	 				"<br>"+" ur Wish book is available name is"+info.getBookName()+"\n"
	 				+"   check ur book below link<br>"+"\n"
	 		+" http://localhost:4200/wish<br>"
	
	 		+ "</body>"
	 		+ " </html>" ;
											
											emailData.setSubject("Notification in WishList");
									
											emailData.setBody(body);
									
											em.sendMail("sandeepkumarrayala@gmail.com", 
													emailData.getSubject(), emailData.getBody());
									 
								}
							}
							
						}
//							}//if id equating
//							}//wish book
//						}//wishbookw for
						info.setUpdatedDateAndTime(LocalDateTime.now());
						repository.save(info);
						return true;
					}
				}
				else 
				{
					throw new UserException("Your are not Authorized User");
				}
			}
		 else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

	@Transactional
	@Override
	public boolean deleteBook(long bookId, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
				String fetchRole = userRole;

			if (fetchRole.equals("seller") ) {
				Book info = repository.fetchbyId(bookId);
				if (info != null) {
					repository.deleteByBookId(bookId);
					return true;
				}
			} else {
				throw new UserException("Your are not Authorized User");
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}


	@Transactional
	@Override
	public boolean editBookStatus(long bookId, String status, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			Book info = repository.fetchbyId(bookId);
			if (info != null) {
				repository.updateBookStatusByBookId(status, bookId);
				return true;
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}

	@Transactional
	@Override
	public List<Book> getAllOnHoldBooks(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> approvedOnHoldBooks = repository.getAllOnHoldBooks();
			return approvedOnHoldBooks;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	public List<Book> getAllRejectedBooks(String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			List<Book> rejectedBooks = repository.getAllRejectedBooks();
			return rejectedBooks;
		} else {
			throw new UserException("User doesn't exist");
		}

	}

	/**
	 * This controller is for getting 12 approval books per page! it can search book
	 * based on there autherName it can sort the book by anything like price,
	 * book_name, book_id etc, it can order the book both asc and desc order default
	 * will be desc order it can return the book based on there passing url
	 * paramater
	 * 
	 * @param searchByBooKName example (" ", book name, raju, etc)
	 * @param page             Example (" ", 1,2,3,4.........)
	 * @param sortBy           example (" ", book_id, price, created_date_and_time
	 *                         etc)
	 * @param order            (" ", asc,desc,)
	 * @return 12 books and number of page and everything
	 */
	@Override
	public Page<Book> getBookAproval(Optional<String> searchBy, Optional<Integer> page, Optional<String> sortBy,
			Optional<String> order) {
		if (order.equals(Optional.ofNullable("asc"))) {
			return repository.findByBookName(searchBy.orElse("_"),
					PageRequest.of(page.orElse(0), 12, Sort.Direction.ASC, sortBy.orElse("book_id")));
		} else {
			return repository.findByBookName(searchBy.orElse("_"),
					PageRequest.of(page.orElse(0), 12, Sort.Direction.DESC, sortBy.orElse("book_id")));
		}
	}

	@Override
	public List<Book> getAllAprovedBook() {
		List<Book> approvedBooks = repository.getAllApprovedBooks();
		return approvedBooks;
	}


	@Override
	public boolean writeReviewAndRating(String token, RatingReviewDTO rrDTO, Long bookId) {
//		Long uId = generate.parseJWT(token);
//		Users user = userRepository.getUserById(uId);
//		Book book = repository.fetchbyId(bookId);
//		boolean notExist =  book.getReviewRating().stream().noneMatch(rr -> rr.getUser().getUserId()==uId);
//		if(notExist) {
//			ReviewAndRating rr = new ReviewAndRating(rrDTO);
//			rr.setUser(user);
//			book.getReviewRating().add(rr);
//			rrRepository.save(rr);
//			repository.save(book);
//		}
//		else {
//			ReviewAndRating rr = book.getReviewRating().stream().filter(r -> r.getUser().getUserId()==uId).findFirst().orElseThrow(() -> new BookAlreadyExist("Review doesnot exist"));
//			rr.setRating(rrDTO.getRating());
//			rr.setReview(rrDTO.getReview());
//			rrRepository.save(rr);
//			repository.save(book);
//
//		}
		
		Long userId = generate.parseJWT(token);
		Users user = userRepository.getUserById(userId);
		ReviewAndRating review = rrRepository.getBookReview(bookId , user.getName());
		if(review==null) {
			ReviewAndRating rr = new ReviewAndRating(rrDTO);
			rr.setBookId(bookId);
			rr.setUserName(user.getName());
			rrRepository.save(rr);
			return true;
			
		}
		return false;

	}

	@Override
	public List<ReviewAndRating> getRatingsOfBook(Long bookId) {

//		Book book=repository.fetchbyId(bookId);
//
//		return book.getReviewRating();
		return rrRepository.getreviews(bookId);
	}
	
	@Override
	public double avgRatingOfBook(Long bookId) {
		double rate=0.0;
		try {
		rate = repository.avgRateOfBook(bookId);
		System.out.println("rate getted:"+rate);
		}catch(Exception e)
		{
			System.out.println("No rating");
		}
		return rate;
	}

	@Override
	public Integer getBooksCount() {
		
		return repository.getAllApprovedBooks().size();
	}
	
	@Transactional
	@Override
	public boolean uploadBookImage(long bookId, String imageName, String token) {
		Long id;

		id = (long) generate.parseJWT(token);
		Users userInfo = userRepository.getUserById(id);
		if (userInfo != null) {
			String userRole = userInfo.getRole();
			System.out.println("actual Role is " + userRole);
			String fetchRole = userRole;

			if (fetchRole.equals("seller") ) {
				Book info = repository.fetchbyId(bookId);
				if (info != null) {
					info.setImage(imageName);
					repository.save(info);
					return true;
				}
			}
		} else {
			throw new UserException("User doesn't exist");
		}

		return false;
	}
	
	@Transactional
	@Override
	public List<Book> sortBookByRate() {
		
		List<Book> books = repository.getAllApprovedBooks();
		System.out.println("Approved books:"+books);
		List<Book> sortBook = books.stream().sorted((book1,book2)->(avgRatingOfBook(book1.getBookId())<avgRatingOfBook(book2.getBookId()))?1:(avgRatingOfBook(book1.getBookId())>avgRatingOfBook(book2.getBookId()))?-1:0).collect(Collectors.toList());
		System.out.println("After sorting:"+sortBook);
		return sortBook;
	}

}
