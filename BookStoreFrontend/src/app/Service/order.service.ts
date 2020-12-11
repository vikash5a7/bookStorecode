import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs/internal/Subject';
import { HttpserviceService } from './httpservice.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
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
  placeOrder(bookId: any, AddressId: any): Observable<any> {
    // tslint:disable-next-line: max-line-length
    return this.http.post(`${environment.BASE_URL}/${environment.PLACE_ORDER}${AddressId}&bookId=${bookId}`,
    {}, {headers: new HttpHeaders({token: localStorage.token})});
  }
}
