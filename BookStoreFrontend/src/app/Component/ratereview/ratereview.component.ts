import { Component, OnInit } from '@angular/core';
import { BookModule } from 'src/app/Model/book/book.module';
import { BookService } from 'src/app/Service/book.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-ratereview',
  templateUrl: './ratereview.component.html',
  styleUrls: ['./ratereview.component.scss']
})
export class RatereviewComponent implements OnInit {


  constructor(
    private bookService: BookService,
    private router: Router,
    private data: BookService,
    public dialog: MatDialog,
    private matSnackBar: MatSnackBar,
    private route: ActivatedRoute,
  ) { }
  bookId: any;
  ratings: Array<any> = [];
  rate: any;
  visible: boolean;
  isAdded: boolean;
  isListed: boolean;
  book: BookModule;
  bookImage: any;
  bookName: any;
  bookAuthor: any;
  bookPrice: any;
  bookDescription: any;
  sellerName: any;
  show: boolean;

  totalRate = 0;
  ratenumber: number=0;
  color: any;
  total: any;
  reviewList =new Array<any>();
  rev:string;
  user=new Array<any>();

  ngOnInit(): void {
    this.bookId = this.route.snapshot.paramMap.get('bookId');
    this.getBookById();
    this.getRatings();
    console.log('bookid ', this.bookId);
    
    // this.getRateOfBookById();
  }

  getBookById() {
    this.bookService.getOneBookById(this.bookId).subscribe((response: any) => {
      console.log(response);
      this.book = response.obj;
      console.log("get book by id:" ,this.book);
      console.log(this.book, 'kkkkkkkk');
     });
  }
  getRateOfBookById() {
    this.bookService.getRateOfBookById(this.bookId).subscribe((response: any) => {
      if (response.obj != null) {
        this.rate = response.obj ;
        if (this.rate === undefined) {
        console.log('book average rate ', this.rate);
        }
      }
    });
  }

  rateNow() {
    // if (this.visible) {
      // localStorage.setItem("totalRate", this.total);
      this.router.navigate(['books/ratingandreview/' + this.bookId]);
    // }
  }

  getRatings() {
    this.bookService
    .getReview(this.bookId)
    .subscribe((response: any) => {
      this.ratings = response.obj;
      console.log('rate and reviews for book ' + this.ratings);

      // tslint:disable-next-line: prefer-const
      // tslint:disable-next-line: forin
      for (const index in this.ratings) {
        this.rate = this.ratings[index];
        this.totalRate += this.rate.rating;
        this.ratenumber += 1;
        this.total = this.totalRate;
        this.rev = this.ratings[index].review;
        this.user = this.ratings[index].userName;
    
        console.log("user:",this.user);
       
        var p={name:this.user,review:this.rev,rating:this.ratings[index].rating};
        this.reviewList.push(p);
        console.log("after push:",this.reviewList);
      }
      if (this.ratenumber > 1) {
          this.totalRate = this.totalRate / this.ratenumber;
          this.total = Number.parseFloat(this.totalRate + '').toFixed(1);
        }
      if (this.totalRate >= 3 || this.totalRate >= 2) {
          this.color = 'rgb(245, 182, 110)';
        }
      if (this.totalRate >= 4) {
          this.color = 'rgb(16, 136, 16)';
        }
      if (this.totalRate < 2) {
          this.color = 'rgb(216, 69, 59)';
        }
      });
  }


  addToCart(bookId: any) {
    if (localStorage.getItem('token') === null) {
      this.matSnackBar.open('Please Login first', 'ok', {
        duration: 5000
      });
      sessionStorage.setItem(bookId, bookId);
      this.isAdded = true;
      this.router.navigateByUrl('login');
    }
    sessionStorage.setItem(bookId, bookId);
    this.ngOnInit();

    // if (this.visible) {
    //   this.bookService.addToCart(this.bookId).subscribe((response: any) => {
    //     this.data.changeMessage("count");
    //     console.log(response["obj"]);
    //     this.isAdded = response.obj;
    //     this._matSnackBar.open("Book added to cart", "ok", {
    //       duration: 1000,
    //     });
    //   });
    // } else {
    //   const dialogRef = this.dialog.open(LoginComponent);
    //   dialogRef.afterClosed().subscribe((result) => {
    //     window.location.reload();
    //   });
    //   this._matSnackBar.open("please login", "ok", {
    //     duration: 1000,
    //   });
    // }
  }

  // adding book to wish list if user login
  addToWishlist() {
    // if (this.visible) {
    //   this.bookService.addToWishList(this.bookId).subscribe((response: any) => {
    //     console.log(response["obj"]);
    //     this.isListed = response["obj"];
    //     this._matSnackBar.open("Book added to wishlist", "ok", {
    //       duration: 1000,
    //     });
    //   });
    // } else {
    //   const dialogRef = this.dialog.open(LoginComponent);
    //   dialogRef.afterClosed().subscribe((result) => {
    //     window.location.reload();
    //   });
    //   this._matSnackBar.open("please login", "ok", {
    //     duration: 1000,
    //   });
    // }
  }
  isAddedToWishList() {
    // this.bookService
    //   .isAddedToWishList(this.bookId)
    //   .subscribe((response: any) => {
    //     this.isListed = response["obj"];
    //   });
  }
}
