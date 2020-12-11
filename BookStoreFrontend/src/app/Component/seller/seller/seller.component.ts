import { Component, OnInit } from '@angular/core';
import { TokenService } from 'src/app/Service/token.service';
import { Router } from '@angular/router';
import { BookService } from 'src/app/Service/book.service';
import {  MatSnackBar } from '@angular/material/snack-bar';

import {  MatDialog } from '@angular/material/dialog';
import { AddbookComponent } from '../../addbook/addbook.component';
import { UpdateBookComponent } from '../../update-book/update-book.component';
import { UploadBookImageComponent } from '../../addbook/upload-book-image/upload-book-image.component';
import {ActivatedRoute,ParamMap} from '@angular/router';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.scss']
})
export class SellerComponent implements OnInit {
  constructor(private service: BookService , private dialog: MatDialog,
              private matSnackBar: MatSnackBar,private _route:ActivatedRoute

  ) { }
  bookSearch: any;
  name: string = null;
  books: any;
  status: string;
  
  sellerBooks:boolean=false;
  orderBooks:boolean=false;

  private param:any;
  
  ngOnInit(): void {
    this.service.autoRefresh$.subscribe(() => {
      this._route.queryParams.subscribe
    (params=>
      {this.param=params['book'];
      if (this.param == "sellerbook") 
      {
      this.sellerBooks=true;
      this.orderBooks=false

      }
      if(this.param == "order")
      {
        this.sellerBooks=false;
        this.orderBooks=true;
      }
    });
       this.getallBooks();
    });

    this._route.queryParams.subscribe
    (params=>
      {this.param=params['book'];
      if (this.param == "sellerbook") 
      {
      this.sellerBooks=true;
      this.orderBooks=false

      }
      if(this.param == "order")
      {
        this.sellerBooks=false;
        this.orderBooks=true;
      }
    });

    this.getUserName();
    this.getallBooks();
    this.getSearchBookData();
  }
  
  getallBooks() {
    this.sellerBooks=true;
    this.orderBooks=false;
    console.log('inside seller book meth.....');
    this.service.getallBooks().subscribe( response => {
      this.books = response.obj;
      console.log('All books ', this.books);
    });

  }

  deleteBook(bookId) {
    this.service.deleteBook(bookId).subscribe((message) => {
      if (message.statusCode === 202) {
        this.matSnackBar.open('Book Deleted Successfully', 'OK', {
          duration: 4000,
        });
    } else {
      this.matSnackBar.open('Error in Book Deletion', 'ok', { duration: 4000 });
    }
    });
  }


  openImageDialog(bookId): void {
    const dialogRef = this.dialog.open(UploadBookImageComponent, {
      width: '25rem',
      panelClass: 'custom-dialog-container',
      data: { bookId },
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  editBook(book: any): void {
    const dialogRef = this.dialog.open(UpdateBookComponent, {
      width: '25rem',
      height: 'auto',
      panelClass: 'custom-dialog-container',
      data: {
        bookName: book.bookName,
        authorName: book.authorName,
        price: book.price,
        noOfBooks: book.noOfBooks,
        bookDetails: book.bookDetails,
        bookId: book.bookId,
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  addBook() {
    const dialogRef = this.dialog.open(AddbookComponent, {
      width: '25rem',
      panelClass: 'custom-dialog-container',
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  verifyBook(bookId: any) {
  this.status = 'OnHold';
  this.service.verifyBook(bookId, this.status).subscribe((message) => {
      if (message.statusCode === 202) {
        this.matSnackBar.open('Request sent Successfully', 'OK', {
          duration: 4000,
        });
    } else {
      this.matSnackBar.open('Error in Book Deletion', 'ok', { duration: 4000 });
    }
    });
  }

  getUserName() {
   this.name = localStorage.getItem('Name');
  }

  getSearchBookData() {
    this.service.getSearchBookData().subscribe((message) => {
      console.log('search data', message.books);
      this.bookSearch = message.books;
    });
  }

}
