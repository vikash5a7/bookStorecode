import { Component, OnInit } from '@angular/core';
import { MatSidenavContent } from '@angular/material/sidenav';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {



  opened = true;
  public opened2 = false;

  isUser = false;
  isSeller = false;
  isAdmin = false;
  role: string;
  isLogin = false;

  constructor(
    private titleService: Title
  ) { }
  nameEventHander($event: any) {
    this.opened2 = $event;
    console.log('2', this.opened2);
  }

  ngOnInit(): void {
    this.role = localStorage.getItem('role');
    this.setTitle('Bookstore');
    console.log('role check toolbar', this.role);
    if (this.role === 'admin') {
     this.isAdmin = true;
     this.isLogin = true;
   }
    if (this.role === 'seller') {
     this.isSeller = true;
     this.isLogin = true;
   }
    if (this.role === 'user') {
     this.isUser = true;
     this.isLogin = true;
     console.log('is user ', this.isUser);
   }
  }
  public setTitle( dashboard: string) {
    this.titleService.setTitle( dashboard );
    }
}
