import { Component, OnInit , Inject} from '@angular/core';
import {  MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import {  MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BookService } from 'src/app/Service/book.service';
@Component({
  selector: 'app-upload-book-image',
  templateUrl: './upload-book-image.component.html',
  styleUrls: ['./upload-book-image.component.scss']
})
export class UploadBookImageComponent implements OnInit {
  imageForm: FormGroup;
  constructor( public dialogRef: MatDialogRef<UploadBookImageComponent>,
               @Inject(MAT_DIALOG_DATA) public data: any,
               private bookService: BookService,
               private snackbar: MatSnackBar,
               private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.imageForm = this.formBuilder.group({
      name: [''],
      imageFile: [''],
    });
  }
  onSelectedFile(event) {
    if (event.target.files.length > 0) {
      const imageFile = event.target.files[0];
      this.imageForm.get('imageFile').setValue(imageFile);
    }
  }

  onSubmit() {
    this.dialogRef.close();
    const formData = new FormData();
    formData.append('imageFile', this.imageForm.get('imageFile').value);
    this.bookService.uploadBookImage(this.data.bookId, formData)
      .subscribe(
        (message) => {
          this.snackbar.open(message.response, 'ok', {
            duration: 4000,
          });
          this.dialogRef.close(1);
        },
        (error: any) => {
          console.log(error);
        }
      );
  }

}
