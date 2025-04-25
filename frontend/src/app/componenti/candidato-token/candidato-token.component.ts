import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-candidato-token',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './candidato-token.component.html',
  styleUrl: './candidato-token.component.css'
})
export class CandidatoTokenComponent {

  tokenCandidato: string = ''
  emailCandidato: string = ''

  ngOnInit() {
    this.tokenCandidato = history.state.token
    this.emailCandidato = history.state.email
  }

}
