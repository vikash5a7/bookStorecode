import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class BookModule {
  [x: string]: any;

  bookId: number;
  BookModule: string;
  bookDetails: string;
  authorName: string;
  bookName: string;
  price: number;
  noOfBooks: number;
  image: string;
  createdDateAndTime: Date;
  status: string;
 }
