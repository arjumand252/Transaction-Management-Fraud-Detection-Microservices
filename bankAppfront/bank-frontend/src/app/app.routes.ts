import { provideRouter, Routes } from '@angular/router';
import { LoginComponent } from './component/login/login.component';
import { AccountsComponent } from './component/accounts/accounts.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { PendingTransactionsComponent } from './pending-transactions/pending-transactions.component';
import { AddClerkComponent } from './add-clerk/add-clerk.component';
import { ViewClerksComponent } from './view-clerks/view-clerks.component';
import { ManagerDashboardComponent } from './component/manager-dashboard/manager-dashboard.component';
import { ClerkDashboardComponent } from './component/clerk-dashboard/clerk-dashboard.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'transactions', component: TransactionsComponent },
  { path: 'pending-transactions', component: PendingTransactionsComponent },
  { path: 'add-clerk', component: AddClerkComponent },
  { path: 'view-clerks', component: ViewClerksComponent }
];

export const appRouting = provideRouter(routes);