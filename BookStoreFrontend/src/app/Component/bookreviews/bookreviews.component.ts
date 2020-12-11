import { Component, OnInit ,Inject} from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BookService } from 'src/app/Service/book.service';
import {MatSnackBarModule, MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'app-bookreviews',
  templateUrl: './bookreviews.component.html',
  styleUrls: ['./bookreviews.component.scss']
})
export class BookreviewsComponent implements OnInit {

  bookId:number;
  constructor(@Inject(MAT_DIALOG_DATA) public data : any,private bookService: BookService , private snakbar: MatSnackBar) { 
    this.bookId = data.bookId;
    console.log("bookid for review:",this.bookId);
  }

  ngOnInit(): void {
    this.getReviews();
  }
  reviews = new Array<any>();
  reviewList =new Array<any>();
  rev:string;
  user=new Array<any>();
  color: string;
  totalRate:any;

  getReviews()
  {
    this.getRateOfBook(this.bookId);
this.bookService.getReview(this.bookId).subscribe((response: any) => {

  console.log("Review response:",response.obj);
  this.reviews=response.obj;
  console.log("Reviews stored:",response.obj['review']);
  for (var index in this.reviews) {
    this.rev = this.reviews[index].review;
    this.user = this.reviews[index].userName;

    console.log("user:",this.user);
   
    var p={name:this.user,review:this.rev,rating:this.reviews[index].rating};
    this.reviewList.push(p);
    console.log("after push:",this.reviewList);
  
  }

}
);
  }

  getRateOfBook(bookId:number)  {
    console.log("book id for avgrate:",bookId);
    this.bookService.getRateOfBookById(bookId).subscribe(

      (response: any) => {
        console.log('response', response);
        console.log('rate of books:', response.obj);
        this.totalRate= response.obj;
        
        }
     
    );
   
  }


  // reviewList=
  //   [
  //     { 
  //       name: "Nayan", review: "This book is a Norse Arabian Nights. Each section is a honeycomb. Stories are nested in stories and crack open to reveal rumor and anecdote, prose poems, tendrils of myth" 
  //     },
  //     { 
  //       name: "Jhon", review: "The opening story’s incessant hedging about language—meant, in part, to parody, ad nauseam, the almost paranoiac way that our language about identity tends to be policed" 
  //     },
  //     { 
  //       name: "michael", review: "The book—though an absorbing and well-crafted work of fiction capable of standing on its own, without the support of biography—is almost impossible to consider independently of the knowledge of where its author’s life overlaps with his art" 
  //     },
  //   ]

}
