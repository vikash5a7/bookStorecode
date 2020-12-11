import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpserviceService } from './httpservice.service';
import { Subject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  // tslint:disable-next-line: variable-name
  private _autoRefresh$ = new Subject();
  private  addwhistlistUrl = environment.WISHLIST_ADD;
  private baseUrl = environment.BASE_URL;
  private httpOptions = {headers: new HttpHeaders({'content-type': 'application/json'})};
  get autoRefresh$() {
    return this._autoRefresh$;
  }
  constructor(private http: HttpClient, private httpService: HttpserviceService) { }

  addToWishlist(bookId: any): Observable<any> {
     console.log('----------------bookid=  ', bookId);
     console.log('${this.baseUrl}/${this.addwhistlistUrl}/${bookId}' + '---------------wishlist url');
     return this.httpService
      .post(`${this.baseUrl}/${this.addwhistlistUrl}/${bookId}`, {}, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }
  getWishllistBooks() {
    // tslint:disable-next-line: max-line-length
    // console.log(`${this.baseUrl}/${environment.WISHLIST_GET}`);
    return this.httpService.get(`${this.baseUrl}/${environment.WISHLIST_GET}`, {headers: new HttpHeaders({token: localStorage.token})});
  }
  getWishlistCount(): Observable<any> {
    console.log('get itmes from cart');
    // tslint:disable-next-line: max-line-length
    return this.httpService.get(`${this.baseUrl}/${environment.WISHLIST_COUNT}`, {headers: new HttpHeaders({token: localStorage.token})});
  }

  removeFromWishList(orderId: number) {
    console.log('removeFromWishList');
    console.log(`${this.baseUrl}/${environment.WISHLIST_REMOVE}` + orderId);
    // tslint:disable-next-line: max-line-length
    return this.httpService.delete(`${this.baseUrl}/${environment.WISHLIST_REMOVE}` + orderId, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }
}
