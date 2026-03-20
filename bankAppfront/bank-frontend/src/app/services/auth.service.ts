import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authHeader!: HttpHeaders;
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  // login(username: string, password: string) {
  //   this.authHeader = new HttpHeaders({
  //     Authorization: 'Basic ' + btoa(username + ':' + password)
  //   });
  //   const basicAuth = btoa(username + ':' + password);

  //   localStorage.setItem('auth', basicAuth);

  //   return this.http.get(this.baseUrl + '/v1/accounts', {
  //     headers: {
  //       Authorization: basicAuth
  //     }
  //   });

  // }
  login(username: string, password: string) {
    return this.http.post<any>(this.baseUrl + '/auth/login', {
      username: username,
      password: password
    }).pipe(
      tap((response: { token: string; }) => {
        localStorage.setItem('auth', response.token);
      })
    );
  }

  // logout() {
  //   localStorage.removeItem('auth');
  // }
  logout() {
    localStorage.removeItem('token');
  }

  // isLoggedIn(): boolean {
  //   return !!localStorage.getItem('auth');
  // }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  // getAuthHeader() {
  //   return this.authHeader;
  // }
  getAuthHeader() {
    const token = localStorage.getItem('token');
    return new HttpHeaders({
      Authorization: 'Bearer ' + token
    });
  }
}
