import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; // <-- import this


@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls:['./login.component.css'],
  imports: [FormsModule, CommonModule]
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  error: string = '';

  constructor(private auth: AuthService, private router: Router) {}

  login() {
    if (!this.username || !this.password) {
      this.error = 'Username and password cannot be empty';
      return;
    }
    console.log("Login clicked");

    this.auth.login(this.username, this.password).subscribe({
      next: () => {
        // this.router.navigate(['/accounts']); works
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.error = "Invalid credentials";
      }
    });
  }
}
