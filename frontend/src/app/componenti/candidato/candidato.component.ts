import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { Router, RouterModule } from '@angular/router';
import { TestService } from '../../servizi/test.service';

@Component({
  selector: 'app-candidato',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    MatTableModule,
    MatTable
  ],
  templateUrl: './candidato.component.html',
  styleUrl: './candidato.component.css'
})
export class CandidatoComponent {

  titoloTest: string = ''

  colonne: string[] = ['ordine', 'titolo', 'tipologia', 'difficolta']

  listaDomande: any = []
  domandeFinalliTabella = new MatTableDataSource<any>([])

  tokenCandidato: string = ''

  constructor(private servizioTest: TestService, private router: Router) { }

  ngOnInit() {
    this.titoloTest = history.state.titolo
    this.listaDomande = history.state.domande || []
    this.listaDomande.forEach((ordineDomanda: any, i: number) => {
      ordineDomanda.ordine = i + 1
    })
    this.domandeFinalliTabella.data = [...this.listaDomande]
  }

  creaTest(form: NgForm) {
    this.servizioTest.creaTest({
      interviewTitle: this.titoloTest,
      questions: this.listaDomande.map((domanda: any) => ({
        id: domanda.id,
        order: domanda.ordine
      })),
      email: form.value.email
    }).subscribe({
      next: (token: any) => {
        this.tokenCandidato = token.candidateToken
      },
      error: () => {
        console.error('Email non inviata')
      },
      complete: () => {
        this.router.navigate(['/home/candidato-token'], { state: { token: this.tokenCandidato, email: form.value.email } })
      }
    })
  }

}
