import { Component, OnInit, Input } from '@angular/core';
import { BookModule } from 'src/app/Model/book/book.module';
import { ActivatedRouteSnapshot, ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BookService } from 'src/app/Service/book.service';

@Component({
  selector: 'app-giverate',
  templateUrl: './giverate.component.html',
  styleUrls: ['./giverate.component.scss']
})
export class GiverateComponent implements OnInit {

  constructor(
    private snackBar: MatSnackBar,
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}
  // tslint:disable-next-line: no-input-rename
  @Input('starCount')  starCount = 5;
  color: string;
  private snackBarDuration = 2000;
  ratingArr = [];
  rating: number;
  book: BookModule;
  bookId: any;
  review: any;
  totalRate: any;

  bookImage: any;
  bookName: any;
  bookAuthor: any;
  token: any;

  ngOnInit(): void {
    this.bookService.autoRefresh$.subscribe(() => {
      this.getRateOfBook(this.bookId);
    });
    this.bookId = this.route.snapshot.paramMap.get('bookId');
    console.log('bookId:', this.bookId);
    this.token = this.route.snapshot.paramMap.get('token');
    console.log('token:', this.token);
    this.getBookById();
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
    this.getRateOfBook(this.bookId);
    this.getColor();
  }


  onClick(rating: any) {
    this.snackBar.open('You rated ' + rating + ' / ' + this.starCount, '', {
      duration: this.snackBarDuration,
    });
    this.rating = rating;
    return false;
  }

    showIcon(index: number) {
    if (this.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }
  }

  getBookById() {
    console.log('get book called');
    this.bookService.getOneBook(this.bookId , this.token).subscribe((response: any) => {
      if (response.obj != null) {
        this.book = response.obj;
        this.bookImage = response.obj.image;
        this.bookAuthor = response.obj.authorName;
        this.bookName = response.obj.bookName;
      }
    });
  }

  submitRate() {
    const data = {
      rating: this.rating,
      review: this.review,
    };
    console.log('rating is', data.rating);
    console.log('review is ', data.review);
    this.bookService
      .ratingandreview(this.bookId, data , this.token)
      .subscribe((response: any) => {
        console.log('submit rate response:', response);
        this.snackBar.open(response.response, 'ok', { duration: 2000 });
        this.router.navigateByUrl('books');
      },
      (error: any) => {
        this.snackBar.open(error.error.message, 'ok', { duration: 2000 });
      }

      );
  }


  getColor() {
    if (this.totalRate >= 3 || this.totalRate >= 2) {
      this.color = 'rgb(245,182,110)';
    }
    if (this.totalRate >= 4) {
      this.color = 'rgb(16,136,16)';
    }
    if (this.totalRate < 2) {
      this.color = 'rgb(250,0,0)';
    }
  }

  getRateOfBook(bookId: number)  {
    console.log('book id for avgrate:', bookId);
    this.bookService.getRateOfBookById(bookId).subscribe(

      (response: any) => {
        console.log('response', response);
        console.log('rate of books:', response.obj);
        this.totalRate = response.obj;

        }

    );

  }

}
