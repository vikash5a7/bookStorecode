// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: true,


  // BASE_URL: 'http://localhost:8081/',
  // BookUrl: 'http://localhost:8081/',
  // getallbooksurl: '',
  BASE_URL: 'http://localhost:8080',
  BookUrl: 'http://localhost:8080',
  getallbooksurl: 'books/',


  // BASE_URL: 'http://localhost:8080',
  // BookUrl: 'http://localhost:8080',
  // getallbooksurl: '',
  // BASE_URL: 'http://localhost:8080',
  // BookUrl: 'http://localhost:8080',
  // getallbooksurl: 'books/getAllBooks',


  getbookbyIdurl: 'books/',
  addandupdatecartUrl: 'books/addandupdatecart',
  sorting: 'books/sorting',
  SortNewestArrival: 'books/unsorting',
  cusUrl : 'books/pagewise',

  addbooks: 'books',
  deleteBook: 'books',
  editBook: 'books',
  verifyBook: 'books',
  addBookImage: 'books/bookimage',
  CartUrl: 'http://localhost:8080/',
  addUrl: 'customers/addcustomer',
  addtocart: 'carts/addcart',
  getbookprice: 'getbookprice',
  getReview: 'books/getratereviews',

  quantity: 'http://localhost:8080/',
  addbooksquantity: 'addbooksquantity',
  USER_REGISTRATION: 'registration',
  USER_LOGIN: 'login',
  USER_FORGET_PASSWORD: 'forgotpassword',
  USER_RESETPASSWORD: 'update',
  ADDCART: '/bookstore/v3/cart/addbookCart/',
  COUNT_BOOKS_IN_CART: '/bookstore/v3/cart/bookCount',
  DEC_BOOKS_QUANTITY: '/bookstore/v3/cart/decreaseQuantityPrice?bookId=',
  INC_BOOKS_QUANTITY: '/bookstore/v3/cart/increasebooksquantity?bookId=',
  REMOVE_BOOKS_FROM_CART: '/bookstore/v3/cart/removeCartBooks',
  GET_BOOKS_FROM_CART: '/bookstore/v3/cart/getcartbooks',
  GET_ADDRESS_BY_ADDRES: '/address/users',
  UPDATE_ADDRESS: '/address/updateAddress',
  ADD_ADDRESS: '/address/add',
  PLACE_ORDER: '/bookstore/placeOrder?addressId=',
  WRITE_REVIEW: 'books/ratingreview?bookId=',
  GET_REVIEWS: 'books/ratingreviews/?bookId=',

  ratereview: 'books/getratereviews/?bookId=',

  adminUrl: 'http://localhost:8080/',
  approveBook: 'admin/update/',
  rejectBook: 'admin/update/',
  unVerifiedBooks: 'admin/books',
  rejectedBooks: 'admin/books',
  approvedBooks: 'admin/books',
  avgrateofbook: 'books/avgrate?bookId=',
  getallOrderedBooks: 'bookstore/getOrdersByAdmin',
  changeOrderstatus: 'bookstore/orderStatusByAdmin',
  getOrdersByseller: 'bookstore/getOrdersByseller',
  getSortedBookByRate: 'books/sortbyrate',

  WISHLIST_ADD: 'bookstore/v3/wishlist/addbookWishlist',
  WISHLIST_GET: 'bookstore/v3/wishlist/getwishbooks',
  WISHLIST_COUNT: 'bookstore/v3/wishlist/wishlistcount',
  WISHLIST_REMOVE: 'bookstore/v3/wishlist/removeWishlist/',

};
