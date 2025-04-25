import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { LoginService } from '../../servizi/login.service';

@Component({
  selector: 'app-login-utente',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,

  ],
  templateUrl: './login-utente.component.html',
  styleUrl: './login-utente.component.css'
})
export class LoginUtenteComponent {

  messaggio: any
  isMessaggioVisible = false

  controlloVisibilita = false

  isCredenzialiValide = true

  constructor(private servizioLogin: LoginService, private router: Router) { }

  onSubmit(form: NgForm) {
    const username = form.value.username
    const password = form.value.password

    this.servizioLogin.loginUtente(username, password).subscribe({
      next: (response) => {
        localStorage.setItem('username', username)
        localStorage.setItem('password', password)
        this.messaggio = response
        this.isMessaggioVisible = true
        this.isCredenzialiValide = true
      },
      error: (error) => {
        console.error('Error:', error)
        this.isCredenzialiValide = false
      },
      complete: () => {
        this.router.navigate(['/home/home-utente'])
      }
    })
  }

}
