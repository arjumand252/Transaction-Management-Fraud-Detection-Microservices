import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private baseUrl = 'http://localhost:8080/v1/accounts';

  constructor(private http: HttpClient, private authservice: AuthService) {}

  getAll() : Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl);
  }

  getById(id: number) : Observable<any>  {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  create(account: any) {
  return this.http.post(`${this.baseUrl}/addAccount`, account);
  }

  update(id: number, account: any)  : Observable<any>  {
    return this.http.put(`${this.baseUrl}/${id}`, account);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
