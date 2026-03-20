import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClerkService {

  private baseUrl = 'http://localhost:8080/v1/Clerk';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  addClerk(data: any) {
    return this.http.post(
      `${this.baseUrl}/addClerk`,
      data,
      {
        headers: this.authService.getAuthHeader()
      }
    );
  }
  getAllClerks() {
    return this.http.get(
      this.baseUrl,
      {
        headers: this.authService.getAuthHeader()
      }
    );
  }
}