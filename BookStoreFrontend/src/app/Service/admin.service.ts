import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpserviceService } from './httpservice.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private httpService: HttpserviceService) { }

  private adminUrl = environment.adminUrl;
  private approveBook = environment.approveBook;
  private rejectBook = environment.rejectBook;
  private unVerifiedBooks = environment.unVerifiedBooks;
  private rejectedBooks = environment.rejectedBooks;
  private approvedBooks = environment.approvedBooks;

  private token = localStorage.getItem('token');

  private getallOrderedBooks = environment.getallOrderedBooks;
  private changeOrderstatus = environment.changeOrderstatus;
  // tslint:disable-next-line: variable-name
  private _autoRefresh$ = new Subject();

  get autoRefresh$() {
    return this._autoRefresh$;
  }

  // private token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpZCI6M30.rzol7EjZW2exz-O-d40T3FvIem3Lk8kYGTngic_YHHX2_T7c4zMCcjDfzMXtOHehZkP8cW7TDK_tWELwWkkryQ";





  private subject = new Subject<any>();
  public get autoRefresh() {
    return this.subject;
  }

  private httpOptions = {
    headers: new HttpHeaders ({'content-type': 'application/json' , token: this.token})
    };

    getUnverifiedBooks(status: string) {
      // status='Onhold';
      console.log('unverified books ' + this.adminUrl + this.unVerifiedBooks + '?status=' + status);
      return this.httpService.get(this.adminUrl + this.unVerifiedBooks + '?status=' + status, this.httpOptions);
    }

    getRejectedBooks() {

      return this.httpService.get(this.adminUrl + this.rejectedBooks, this.httpOptions);
    }

    getApprovedBooks(status: string) {

      return this.httpService.get(this.adminUrl + this.approvedBooks + '?status=' + status, this.httpOptions);
    }

    approveBooks(noteId: number, status: string): Observable<any> {

      // tslint:disable-next-line: max-line-length
      return this.httpService.put(this.adminUrl + this.approveBook + noteId + '?' + 'status=' + status, '', this.httpOptions).pipe(tap(() => { this.subject.next(); }));
    }

    rejectBooks(noteId: number, status: string): Observable<any> {

      // tslint:disable-next-line: max-line-length
      return this.httpService.put(this.adminUrl + this.rejectBook + noteId + '?' + 'status=' + status, '', this.httpOptions).pipe(tap(() => { this.subject.next(); }));
    }

    getAllOrderedBooks(): Observable<any> {
      console.log('order status url');
      console.log(this.httpService.get(this.adminUrl + this.getallOrderedBooks, this.httpOptions));
      return this.httpService.get(this.adminUrl + this.getallOrderedBooks, {});
     }



     updateOrderStatus(orderId: any, status: any): Observable<any> {
      //  var y:number =+orderId;
       console.log('url ' + this.adminUrl + this.changeOrderstatus + '?orderId=' + orderId + '&status=' + status);


      // return this.httpService.put(this.adminUrl+this.changeOrderstatus+"?orderId="+orderId+"&status="+status,"",this.httpOptions);

       return this.httpService
                             // tslint:disable-next-line: max-line-length
                             .put(this.adminUrl + this.changeOrderstatus + '?orderId=' + orderId + '&status=' + status, '', this.httpOptions);
                            //  .pipe(
                            //   tap(() => {
                            //     this._autoRefresh$.next();
                            //   })
                            // );

      // http://localhost:8080/bookstore/orderStatusByAdmin?orderId=583785&status=in%20progress
     }
  //    @GetMapping(value = "bookstore/orderedbooks/{token}")
 // public ResponseEntity<Response> getOrderlist(@PathVariable("token") String token) throws Exception {

  }
