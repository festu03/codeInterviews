import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../../servizi/login.service';

@Component({
  selector: 'app-login-canditato',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './login-candidato.component.html',
  styleUrl: './login-candidato.component.css'
})
export class LoginCandidatoComponent {

  isTokenValid = true

  constructor(private servizioLogin: LoginService, private router: Router) { }

  onSubmit(form: NgForm) {
    const token = form.value.token
    this.servizioLogin.loginCandidato(token).subscribe({
      next: (response: any) => {

        this.isTokenValid = true
        // console.log(this.isTokenValid)

        localStorage.setItem('token', token)
        localStorage.setItem('nome candidato', response.name)
      },
      error: (error) => {
        console.error(error)
        this.isTokenValid = false
      },
      complete: () => {
        this.router.navigate(['home/home-candidato'])

      }
    })
  }

}
