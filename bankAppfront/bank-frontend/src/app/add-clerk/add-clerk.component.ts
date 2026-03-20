import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ClerkService } from '../services/clerk.service';

@Component({
  selector: 'app-add-clerk',
  standalone: true,
  imports: [FormsModule, CommonModule],
  styleUrl: './add-clerk.component.css',
  templateUrl: './add-clerk.component.html'
})
export class AddClerkComponent {

  clerkName: string = '';
  managerId!: number;
  password: string = '';

  constructor(private clerkService: ClerkService) {}

  submit(form: any) {

  if (form.invalid) {
    return;
  }

  const payload = {
    clerkName: this.clerkName,
    managerId: this.managerId,
    password: this.password
  };

  this.clerkService.addClerk(payload).subscribe({
    next: () => {
      alert('Clerk added successfully');
      form.resetForm();   // proper reset
    },
    error: (err) => {
      console.error(err);
      alert('Error adding clerk');
    }
  });

  }
}