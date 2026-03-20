import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClerkService } from '../services/clerk.service';

@Component({
  selector: 'app-view-clerks',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './view-clerks.component.html',
  styleUrls: ['./view-clerks.component.css']
})
export class ViewClerksComponent implements OnInit {

  clerks: any[] = [];

  constructor(private clerkService: ClerkService) {}

  ngOnInit(): void {
    this.loadClerks();
  }

  loadClerks() {
    this.clerkService.getAllClerks().subscribe({
      next: (data: any) => {
        this.clerks = data;
      },
      error: (err) => {
        console.error(err);
        alert("Error loading clerks");
      }
    });
  }
}