import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import {  MatDialog, MatDialogRef } from '@angular/material/dialog';
import {   MatSnackBar } from '@angular/material/snack-bar';
import { BookService } from 'src/app/Service/book.service';
import { BookModule } from 'src/app/Model/book/book.module';
@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.scss']
})
export class AddbookComponent implements OnInit {
  bookForm: FormGroup;
  bookid;
  constructor( private matSnackBar: MatSnackBar,
               private formBuilder: FormBuilder,
               private bookService: BookService,
               private dialog: MatDialog,
               private dialogRef: MatDialogRef<AddbookComponent>) { }
    private imageFile: string;
  ngOnInit(): void {}

  addbooks: BookModule = new BookModule();
  bookName = new FormControl(this.addbooks.bookName, [
    Validators.required,
    Validators.minLength(3),
    Validators.maxLength(25),
    Validators.pattern("[a-zA-Z ]*"),
  ]);
  authorName = new FormControl(this.addbooks.authorName, [
    Validators.required,
    Validators.minLength(5),
    Validators.maxLength(25),
    Validators.pattern("[a-zA-Z ]*"),
  ]);
  price = new FormControl(this.addbooks.price, [
    Validators.required,
    Validators.minLength(1),
    Validators.pattern("[0-9 ]*"),
  ]);
  noOfBooks = new FormControl(this.addbooks.noOfBooks, [
    Validators.required,
    Validators.minLength(1),
    Validators.pattern("[0-9]*"),
  ]);
  bookDetails = new FormControl(this.addbooks.bookDetails, [
    Validators.required,
    Validators.minLength(20),
    Validators.pattern("[a-zA-Z ]*"),
  ]);


  onSelectedImage(event) {
    if (event.target.files.length > 0) {
      const image = event.target.files[0];
      this.imageFile = image.name;
    }
  }
    onClickaddBook() {
      this.bookService.addBook(this.addbooks, this.imageFile).subscribe(
        (user) => {
          if (user.statusCode === 200) {
            this.matSnackBar.open(user.response, 'ok', {duration: 4000});
            this.dialogRef.close(1);
          }
        },
        (error: any) => {
          this.matSnackBar.open(error.error, 'ok', { duration: 4000 });
          console.log(error);
        }
      );
      if (this.bookForm.invalid) {
        return;
      }
    }
    
    bookNameValidation() {
      return this.bookName.hasError("required") ? "Book Name must be required" : 
             this.bookName.hasError("minlength") ? "Minimum 3 character must be present" : 
             this.bookName.hasError("maxlength") ? "Maximum 25 character allowed" : "";
    }
    bookAuthorValidation() {
      return this.authorName.hasError("required") ? "Author name must be required" : 
             this.authorName.hasError("minlength") ? "Minimum 5 character must be present" : 
             this.authorName.hasError("maxlength") ? "Maximum 25 character allowed" : "";
    }
    bookPriceValidation() {
      return this.price.hasError("required") ? "Price must be required" :
             this.price.hasError('pattern')? "Only numbers allowed":
             this.price.hasError("minlength") ? "Minimum 1 digit must be there" :"";
    }
    noOfBooksValidation() {
      return this.noOfBooks.hasError("required") ? "Total Number of must be required" : 
             this.noOfBooks.hasError('pattern')? "Only numbers allowed":
             this.noOfBooks.hasError("minlength") ? "Minimum 1 digit must be there" :"";
    }
    bookDescriptionValidation() {
      return this.bookDetails.hasError("required") ? "Book Description must required" :
             this.bookDetails.hasError("minlength") ? "Minimum 20 characters  must be there" :"";
    }
   
}
