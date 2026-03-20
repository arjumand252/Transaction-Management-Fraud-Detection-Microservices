import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  baseUrl = 'http://localhost:8080/v1';

  constructor(private http: HttpClient, private authService: AuthService) {}

  deposit(data: { accountId: number; amount: number; clerkId: number }) {
    return this.http.post(
    `http://localhost:8080/v1/accounts/deposit`,  // <-- correct absolute URL
      {
        id: data.accountId,
        amount: data.amount,
        clerkId: data.clerkId
      },
      { headers: this.authService.getAuthHeader() }
    );
  }

  withdraw(data: { accountId: number; amount: number; clerkId: number }) {
    return this.http.post(
    `http://localhost:8080/v1/accounts/withdraw`, // <-- correct absolute URL
      {
        id: data.accountId,
        amount: data.amount,
        clerkId: data.clerkId
      },
      { headers: this.authService.getAuthHeader() }
    );
  }

  transfer(data: any) {
    return this.http.post(`${this.baseUrl}/transfer`, data, {
      headers: this.authService.getAuthHeader()
    });
  }
  // =======================
  // 🔎 GET TRANSACTIONS BY ACCOUNT ID
  // =======================
  getTransactionsByAccountId(id: number) {
    return this.http.get<any[]>(
      `${this.baseUrl}/accounts/${id}/getTransaction`
    );
  }
}
