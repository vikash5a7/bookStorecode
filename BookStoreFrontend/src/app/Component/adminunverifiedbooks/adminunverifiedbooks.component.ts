import { Component, OnInit } from "@angular/core";
import { BookModule } from "src/app/Model/book/book.module";
import { AdminService } from "src/app/Service/admin.service";
import { MatSnackBarModule, MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-adminunverifiedbooks",
  templateUrl: "./adminunverifiedbooks.component.html",
  styleUrls: ["./adminunverifiedbooks.component.scss"],
})
export class AdminunverifiedbooksComponent implements OnInit {
  constructor(
    private adminService: AdminService,
    private snakbar: MatSnackBar
  ) {}

  book = [];
  books = new Array<BookModule>();
  noteId = 1;
  status: string;

  ngOnInit(): void {
    this.getUnApprovedBooks(status);

    // this.dataSource.paginator = this.paginator;
    this.adminService.autoRefresh.subscribe(() => {
      this.getUnApprovedBooks(status);
    });
  }

  getUnApprovedBooks(status: string) {
    this.adminService.getUnverifiedBooks("OnHold").subscribe(
      (response: any) => {
        console.log("response", response);
        console.log("books:", response.obj);
        this.books = response.obj;
      },
      (error: any) => {
        this.snakbar.open(error.error.message, "failed", { duration: 5000 });
      }
    );
  }

  approveBooks(bookId: number, status: string) {
    this.adminService.approveBooks(bookId, status).subscribe(
      (response: any) => {
        this.snakbar.open("book is approved", "success", { duration: 4000 });
      },
      (error: any) => {
        this.snakbar.open(error.error.message, "failed", { duration: 5000 });
      }
    );
  }

  rejectBooks(bookId: number, status: string) {
    this.adminService.rejectBooks(bookId, status).subscribe(
      (response: any) => {
        this.snakbar.open("book is rejected", "success", { duration: 4000 });
      },
      (error: any) => {
        this.snakbar.open(error.error.message, "failed", { duration: 5000 });
      }
    );
  }
}
