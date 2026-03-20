import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ManagerService } from '../services/manager.service';
import { RouterModule } from '@angular/router';
// import { PendingTransactionDTO } from '../models/pending-transaction.model';


@Component({
  selector: 'app-pending-transactions',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './pending-transactions.component.html',
  styleUrls: ['./pending-transactions.component.css']
})
export class PendingTransactionsComponent implements OnInit {
  mgrId: number = 0; // Input field for manager ID
  pendingTx: any[] = [];
  error: string = '';
  loading: boolean = false;

  constructor(private managerService: ManagerService) {}

  ngOnInit(): void {}

  // Fetch pending transactions
  getPendingTransactions() {
    if (!this.mgrId) return;
    this.loading = true;
    this.pendingTx = [];
    this.error = '';

    this.managerService.getPendingTransactions(this.mgrId)
      .subscribe({
        next: (res: any[]) => {
          this.pendingTx = res;
          this.loading = false;
        },
        error: err => {
          if(err.status == 403){
            this.error = 'Access Denied: You are not a manager and are therefore not authorized to view pending transactions.';
          }
          else{ 
            this.error = err.message || 'Something went wrong';
            this.loading = false;
          }
        }
      });
  }
}
