import { Component, OnInit } from '@angular/core';
import { Router , ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  name: string = null;
  private param: any;
  unverified = false;
  orders = false;
  review = false;
  constructor(private router: ActivatedRoute) { }

  ngOnInit(): void {
    // this.getUserName();
    this.router.queryParams.subscribe(params => {
      this.param = params.book;
      if (this.param === 'unverified') {
     this.unverified = true;
     this.orders = false;
     this.review = false;
     console.log('unverifed:', this.unverified);
    }
      if (this.param === 'order') {
    this.orders = true;
    this.unverified = false;
    this.review = false;
    }
      if (this.param === 'review') {
    this.review = true;
    this.orders = false;
    this.unverified = false;
    }
      if (this.param === 'books') {
    this.review = false;
    this.orders = false;
    this.unverified = false;
    }
  });
  }

}
