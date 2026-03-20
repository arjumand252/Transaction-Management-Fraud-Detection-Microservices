import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  constructor(private router: Router, private authService: AuthService) {}

  logout() {
    // Remove token (recommended instead of clearing everything)
    localStorage.removeItem('token'); 
    
    // Navigate to login page
    this.router.navigate(['/']);
  }
}
