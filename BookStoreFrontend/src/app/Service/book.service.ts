import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { HttpserviceService } from './httpservice.service';
import { BookModule } from '../Model/book/book.module';
import { tap, map, catchError } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  // tslint:disable-next-line: variable-name
  private _autoRefresh$ = new Subject();

  get autoRefresh$() {
    return this._autoRefresh$;
  }


  private searchBookData = new Subject<any>();
  private baseUrl = environment.BASE_URL;
  private notesList = new Subject<any>();
  private getReviewUrl = environment.getReview;

  // tslint:disable-next-line: variable-name

  // tslint:disable-next-line: variable-name
  private httpOtions = {
    headers: new HttpHeaders({ 'content-type': 'application/json' })
  };


  constructor(private http: HttpClient, private httpService: HttpserviceService) { }

  private httpOptions = {headers: new HttpHeaders({'content-type': 'application/json'})};


  public getAllApprovedBook(): Observable<any> {
    return this.http.get(`${this.baseUrl}/books/approved?order=asc`);
  }
  public getAllApprovedBookByPage(page: number, sortby ?: string, orderBy ?: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/books/approved?page=${page}&order=${orderBy}&sortBy=${sortby}`);
  }

  getallBooks() {
    console.log('getting all books');
    // tslint:disable-next-line: max-line-length
    return this.httpService.get(`${this.baseUrl}/books/`, {headers: new HttpHeaders({token: localStorage.token})});
  }

  addBook(book: any, imageName: string): Observable<any> {
    return this.httpService
      .post(`${environment.BookUrl}/${environment.addbooks}/${imageName}`, book, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }

  deleteBook(bookId: any): Observable<any> {
    return this.httpService
      .delete(`${environment.BookUrl}/${environment.deleteBook}/${bookId}`, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }

  updateBook(bookId: any, book: any): Observable<any> {
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .put(`${environment.BookUrl}/${environment.editBook}/${bookId}`, book, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }

  verifyBook(bookId: any, status: any): Observable<any> {
    console.log('url ', `${environment.BookUrl}/${environment.verifyBook}/${bookId}/${status}`);

    return this.httpService
      // tslint:disable-next-line: max-line-length
      .put(`${environment.BookUrl}/${environment.verifyBook}/${bookId}/${status}`, ' ', {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }

  uploadBookImage(bookId,  formData): Observable<any> {
    return this.httpService
      // tslint:disable-next-line: max-line-length
      .post(`${environment.BookUrl}/${environment.addBookImage}/${bookId}`, formData, {headers: new HttpHeaders({token: localStorage.token})})
      .pipe(
        tap(() => {
          this._autoRefresh$.next();
        })
      );
  }
  getBokkByid(Bookid: any): Observable<any> {
    console.log('book service callerd', Bookid);
    console.log('book url', `${this.baseUrl}/books/getbook/${Bookid}`);
    return this.http.get(`${this.baseUrl}/books/getbook/${Bookid}`,
       {});
  }

  setSearchBookData(message: any) {
    console.log('set service', message);
    return this.searchBookData.next({ books: message });
  }
  getSearchBookData(): Observable<any> {
    console.log('get service');
    return this.searchBookData.asObservable();
  }
  public getRateOfBookById(bookId: any): Observable<any> {

    console.log( environment.BASE_URL + '/' + environment.avgrateofbook + bookId);
    return this.http.get(
      environment.BASE_URL + '/' + environment.avgrateofbook + bookId,
      {}
    );
  }

  // public getBookById(bookId: any): Observable<any> {
  //   console.log('writring review for bookid ', bookId);
  //   console.log( environment.BASE_URL + environment.getbookbyIdurl + bookId);
  //   return this.http.get(
  //     environment.BASE_URL + environment.getbookbyIdurl + bookId,
  //     {}
  //   );
  // }
  public getBookById(bookId: any): Observable<any> {
    console.log('writring review for bookid ', bookId);
    console.log( environment.BASE_URL + environment.getbookbyIdurl + bookId);
    return this.http.get(
      environment.BASE_URL + environment.getbookbyIdurl + bookId,
      {}
    );
  }
  public ratingandreview(bookId: number, data: any , token: any) {
    console.log('ratingandreview service method bookId :', bookId);
    console.log('ratingandreview service method rate& review dto :', data);
    console.log('token to give rate:', token);
    console.log('url ' + environment.BASE_URL + '/' + environment.WRITE_REVIEW + bookId);
    const tokens = token;

    return this.http
      .put(environment.BASE_URL + '/' + environment.WRITE_REVIEW + bookId, data, {headers: new HttpHeaders({token})})
      .pipe(
        tap(() => {
          this.searchBookData.next();
        })
      );
  }

  public getratingandreview(bookId: number) {
    return this.http.get(environment.BASE_URL + environment.ratereview + bookId, this.httpOptions);
  }

  getInProgressOrderedBooks(): Observable<any> {
    console.log('order status url');
    console.log(this.httpService.get(environment.adminUrl + environment.getOrdersByseller, this.httpOptions));
    return this.httpService.get(environment.adminUrl + environment.getOrdersByseller, {});
   }

  public getReview(bookId: number) {
    console.log('get review url:', `${environment.BASE_URL}/${this.getReviewUrl}?bookId=${bookId}`);
    return this.http.get(`${environment.BASE_URL}/${this.getReviewUrl}?bookId=${bookId}`, this.httpOptions);
  }

  public getSortedBookByRate(): Observable<any> {
    return this.http.get(`${environment.BASE_URL}/${environment.getSortedBookByRate}`, this.httpOptions);
  }

  public getOneBook(bookId: number , token: any) {
    return this.http.get(`${this.baseUrl}/books/getbook/${bookId}`,
    {headers: new HttpHeaders({token})});
  }

  public getOneBookById(bookId: number) {
    return this.http.get(`${this.baseUrl}/books/getbook/${bookId}`,
    this.httpOptions);
  }


}
