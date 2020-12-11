import { Component, OnInit , Inject} from '@angular/core';
import {  MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BookService } from 'src/app/Service/book.service';
import { BookModule } from 'src/app/Model/book/book.module';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.scss']
})
export class UpdateBookComponent implements OnInit {
  bookName = new FormControl(this.data.bookName, [Validators.required]);
  authorName = new FormControl(this.data.authorName, [Validators.required]);
  price = new FormControl(this.data.price, [Validators.required]);
  noOfBooks = new FormControl(this.data.noOfBooks, [Validators.required]);
  bookDetails = new FormControl(this.data.bookDetails, [Validators.required, ]);
  private imageFile: string;

  constructor( @Inject(MAT_DIALOG_DATA) public data: any,
               private bookservice: BookService,
               private activedRoute: ActivatedRoute,
               private router: Router,
               private matSnackBar: MatSnackBar,
               private http: HttpClient,
               public dialogRef: MatDialogRef<UpdateBookComponent>, ) { }
  updatebook: BookModule = new BookModule();
  ngOnInit(): void {
  }

  onSelectedImage(event) {
    if (event.target.files.length > 0) {
      const image = event.target.files[0];
      this.imageFile = image.name;
    }
  }
  updateBook() {

    this.updatebook.bookName = this.data.bookName;
    this.updatebook.authorName = this.data.authorName;
    this.updatebook.price = this.data.price;
    this.updatebook.noOfBooks = this.data.noOfBooks;
    this.updatebook.bookDetails = this.data.bookDetails;
    // this.dialogRef.close();

    setTimeout(() => {
      this.bookservice.updateBook(this.data.bookId, this.updatebook).subscribe(
        (response: any) => {
          if (response.statusCode === 200) {
            this.dialogRef.close({ data: this.updatebook });
            this.matSnackBar.open(response.response, 'undo', {
              duration: 3000,
            });
          } else {
            this.dialogRef.close();
            this.matSnackBar.open('Book not updated...try again', 'undo', {
              duration: 2500,
            });
          }
        },
        (error: any) => {
          this.dialogRef.close();
          this.matSnackBar.open('something went wrong.....!', 'undo', {
            duration: 2500,
          });
        }
      );
    }, 3000); // spinner
  }
}
