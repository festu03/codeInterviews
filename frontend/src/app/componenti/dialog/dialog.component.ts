import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import {
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
} from '@angular/material/dialog';

@Component({
  selector: 'app-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    RouterModule],
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.css'
})
export class DialogComponent {

  constructor(private route: Router) { }

  onClick() {
    this.route.navigate(['/home/domanda'])
  }

}
