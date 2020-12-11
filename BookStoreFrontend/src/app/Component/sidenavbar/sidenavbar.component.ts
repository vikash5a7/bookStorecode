

import { Router, ActivatedRoute,ParamMap} from '@angular/router';

import { Component, OnInit ,ViewChild  } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';



@Component({
  selector: 'app-sidenavbar',
  templateUrl: './sidenavbar.component.html',
  styleUrls: ['./sidenavbar.component.scss']
})
export class SidenavbarComponent implements OnInit {
  @ViewChild('sidenav', { static: true }) public sidenav: MatSidenavModule;
  isSeller = false;
  isAdmin=false;
  role:string;

  constructor(private router:Router,private route:ActivatedRoute,) { }




  ngOnInit() {
   this.role= localStorage.getItem('role');
   console.log('role check sidenav',this.role);
   if (this.role === 'admin') 
   {
     this.isAdmin=true;
   }
   if (this.role === 'seller') 
   {
     this.isSeller=true;
   }
  }
  sellerBooks()
  {
    this.router.navigate(['books'],{queryParams:{book:'unverified'}});
  }

  orders()
  {
    this.router.navigate(['books'],{queryParams:{book:'order'}});
  }

  reviews()
  {
    this.router.navigate(['books'],{queryParams:{book:'review'}});
  }

  books()
  {
    this.router.navigate(['books'],{queryParams:{book:'books'}});
  }


  sellerBook(){
    
      this.router.navigate(['books'],{queryParams:{book:'sellerbook'}});
  }

  orderStatus(){

this.router.navigate(['books'],{queryParams:{book:'order'}});
  }
  
}
