import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-logout-candidato',
  standalone: true,
  imports: [],
  templateUrl: './logout-candidato.component.html',
  styleUrl: './logout-candidato.component.css'
})
export class LogoutCandidatoComponent {

emailCandidato = localStorage.getItem('nome candidato')

  constructor(private authService: AuthService, private router: Router) { }


  disconnessione() {
    this.authService.disconnessione()
    this.router.navigate(['/benvenuto'])
  }
}
