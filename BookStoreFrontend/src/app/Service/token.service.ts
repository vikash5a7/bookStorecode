import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  public valideUser = false;
  public loggedIn = false;
  constructor() { }
  public handle(data) {
    this.set(data);
  }
  // setting token in local
  public set(data) {
    localStorage.setItem('token', data.token);
    localStorage.setItem('email', data.obj.email);
    localStorage.setItem('Name', data.obj.name);
    localStorage.setItem('phone', data.obj.mobileNumber);
  }
  // getting token from the local storage
 public get() {
   return localStorage.getItem('token');
  }
  // Removing item from the local storage
  remove() {
    localStorage.removeItem('token');
    localStorage.removeItem('email');
    localStorage.removeItem('Name');
    localStorage.removeItem('role');
    localStorage.removeItem('phone');
    sessionStorage.clear();
  }
  logedIn(value: boolean) {
  if ( this.get() != null) {
    return this.loggedIn = true;
  }
  }
  loggedStatus() {
  return this.logedIn(this.loggedIn);
  }
}
