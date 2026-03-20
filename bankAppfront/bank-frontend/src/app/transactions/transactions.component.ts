import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../services/transaction.service'
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-transactions',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './transactions.component.html',
  styleUrl: './transactions.component.css'
})
// export class TransactionsComponent implements OnInit{
//   transactions: any;
//   constructor(private transactionService: TransactionService) {}

//   ngOnInit(): void {
//     this.transactionService.getTransactions().subscribe(data =>
//     {this.transactions = data;}
//     );
//   }
// }
export class TransactionsComponent {
  // Track which operation is selected
  selectedOperation: 'transfer' | 'deposit' | 'withdraw' | null = null;

  // Form models
  depositWithdrawData = { amount: 0, clerkId: 0, accountId: 0 };
  transferData = { fromAccId: 0, toAccId: 0, amount: 0, clerkId: 0 };

  // Result or error
  result: any;
  error: string = '';

  // =======================
  // 🔎 SEARCH FEATURE STATE
  // =======================
  searchAccId: number = 0;
  transactionHistory: any[] = [];
  errorMessage: string = '';
  hasSearched = false;

  constructor(private transactionService: TransactionService) {}

  selectOperation(op: 'transfer' | 'deposit' | 'withdraw') {
    this.selectedOperation = op;
    this.result = null;
    this.error = '';
    // reset form
    this.depositWithdrawData = { amount: 0, clerkId: 0, accountId: 0};
    this.transferData = { fromAccId: 0, toAccId: 0, amount: 0, clerkId: 0 };
  }

  submitDeposit() {
    this.transactionService.deposit(this.depositWithdrawData)
      .subscribe({
        next: res => this.result = res,
        error: err => this.error = err.message || 'Something went wrong'
      });
  }

  submitWithdraw() {
    this.transactionService.withdraw(this.depositWithdrawData)
      .subscribe({
        next: res => this.result = res,
        error: err => this.error = err.message || 'Something went wrong'
      });
  }

  submitTransfer() {
    this.transactionService.transfer(this.transferData)
      .subscribe({
        next: res => this.result = res,
        error: (err) => {
          this.error = err.message || 'Something went wrong';
          if (err.error?.message) {
            this.error = err.error.message;
          } else if (typeof err.error === 'string') {
            this.error = err.error;
          } else {
            this.error = "Transfer failed";
          }
        }
      });
  }
  // =======================
    // 🔎 FETCH TRANSACTIONS
    // =======================
    fetchTransactions() {
      this.hasSearched = true;
      this.errorMessage = '';
      if (!this.searchAccId) {
        this.errorMessage = 'Please enter a valid Account ID';
        return;
      }

      this.transactionService
        .getTransactionsByAccountId(this.searchAccId)
        .subscribe({
          next: (data) => {
            this.transactionHistory = data;
            if(!data || data.length === 0){
              this.errorMessage = 'No transactions found for this account.';
            }
          },
          error: (err) => {
            this.transactionHistory = [];
            this.errorMessage = 'No transactions found for this Account ID';
          }
        });
    }
}
