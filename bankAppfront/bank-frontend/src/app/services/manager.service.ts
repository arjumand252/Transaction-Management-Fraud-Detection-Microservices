import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { PendingTransactionsComponent } from '../pending-transactions/pending-transactions.component';
// import { PendingTransactionsComponent } from '../models/pending-transaction.model';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
  baseUrl = 'http://localhost:8080/v1/managers';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getPendingTransactions(mgrId: number) {
    return this.http.get<PendingTransactionsComponent[]>
    (`${this.baseUrl}/${mgrId}/pendingTransactions`, {
      headers: this.authService.getAuthHeader()
    });
  }

  // Later you can add approve/reject transactions endpoints here
}
