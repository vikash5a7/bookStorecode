import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { HttpserviceService } from './httpservice.service';
import { Observable, Subject } from 'rxjs';
import { tap, map, catchError } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class CartService {
  private bookApiUrl = environment.BookUrl;
  private baseUrl = environment.BASE_URL;
  private httpOptions = {headers: new HttpHeaders({'content-type': 'application/json'})};
  // tslint:disable-next-line: variable-name
  private _autoRefresh$ = new Subject();

  get autoRefresh$() {
    return this._autoRefresh$;
  }

  constructor(private http: HttpClient, private httpService: HttpserviceService) { }

  private httpOtions = {
    headers: new HttpHeaders({ 'content-type': 'application/json' })
  };

  post( arr: any): Observable<any> {
    console.log(arr, 'custmerdetails');
    return this.httpService.post(environment.CartUrl + environment.addUrl, arr, '');
  }
  addToCart(bookId: any): Observable<any> {
    return this.httpService
      .post(`${environment.BookUrl}/${environment.ADDCART}/${bookId}`, {}, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }
  increaseBooksQuantity(bookId, CartInfo) {
    console.log('cart details are ', CartInfo);
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .put(`${environment.BookUrl}/${environment.INC_BOOKS_QUANTITY}${bookId}`, CartInfo , {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }

 decreaseBooksQuantity(bookId, CartInfo) {
    console.log('cart details are ', CartInfo);
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .put(`${environment.BookUrl}/${environment.DEC_BOOKS_QUANTITY}${bookId}`, CartInfo , {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }




  removeIteamFromCart(bookId: number) {
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .delete(`${environment.BookUrl}/${environment.REMOVE_BOOKS_FROM_CART}/${bookId}`, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }

  getCartBooksFrom() {
    // tslint:disable-next-line: max-line-length
    return this.httpService.get(`${this.baseUrl}/${environment.GET_BOOKS_FROM_CART}`, {headers: new HttpHeaders({token: localStorage.token})});
  }

  getCartItemCount(): Observable<any> {
    console.log('get itmes from cart');
    // tslint:disable-next-line: max-line-length
    return this.httpService.get(`${this.baseUrl}/${environment.COUNT_BOOKS_IN_CART}`, {headers: new HttpHeaders({token: localStorage.token})});
  }


  addquantity(BookId: any, quantity: any): Observable<any> {
    return this.httpService.post(environment.quantity + environment.addbooksquantity + '/' + BookId + '/' + quantity, '', '');  }
}
