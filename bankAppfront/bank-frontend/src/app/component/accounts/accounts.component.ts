import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-accounts',
  standalone: true,
  imports: [CommonModule, FormsModule],
  styleUrls: ['./accounts.component.css'],
  templateUrl: './accounts.component.html',
})
export class AccountsComponent implements OnInit {

  accounts: any[] = [];

  newAccount: any = {
    name: '',
    balance: 0,
    phone: '',
    date: '',
    nationality: ''
  };
  error: string = '';
  success: string = '';

  constructor(private accountService: AccountService) {}

  ngOnInit(): void {
    console.log("acc comp loaded");
    this.loadAccounts();
  }

  loadAccounts() {
    this.accountService.getAll().subscribe(data => {
      this.accounts = data;
    });
  }

  createAccount(form: NgForm) {
    this.error = '';
    this.success = '';
    console.log("clicked.");
    if (
      !this.newAccount.name ||
      this.newAccount.balance <= 0 ||
      !this.newAccount.phone ||
      !this.newAccount.date ||
      !this.newAccount.nationality
    ) {
      this.error = 'Please enter valid account details.';
      return;
    }

    this.accountService.create(this.newAccount).subscribe(() => {
      this.loadAccounts();

      form.resetForm({
        name: '',
        balance: 0,
        phone: '',
        date: '',
        nationality: ''
      });

      this.success = 'Account created successfully!';
    });
  }

  deleteAccount(id: number) {
    this.accountService.delete(id).subscribe(() => {
      this.loadAccounts();
    });
  }
    searchTerm: string = '';

    get filteredAccounts() {
      return this.accounts.filter(acc => 
        acc.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        acc.id.toString().includes(this.searchTerm)
      );
    }
}
