import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

import { TestService } from '../../servizi/test.service';

import { FormsModule } from '@angular/forms';

import { MarkdownModule } from 'ngx-markdown';
import { MonacoEditorModule } from 'ngx-monaco-editor-v2';

import { Router, RouterModule } from '@angular/router';
import { state } from '@angular/animations';


@Component({
  selector: 'app-risposta',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MarkdownModule,
    MonacoEditorModule,
    RouterModule,
  ],
  templateUrl: './test-risposta.component.html',
  styleUrl: './test-risposta.component.css'
})
export class TestRispostaComponent {

  idInterview: number
  domande: any = []
  nomeCandidato: string

  titolo: string = ''
  descrizione: string = ''

  risposta: string = ''

  editorOptions = { theme: 'vs-dark', language: 'txt' }

  idDomande: number[] = []
  idDomandePrecendenti: number[] = []
  id: any = 0

  constructor(private servizioTest: TestService, private router: Router) { }

  ngOnInit() {
    this.idInterview = history.state.idTest

    this.idDomande = history.state.idDomande
    this.nomeCandidato = history.state.email
    console.log(this.nomeCandidato)

    this.servizioTest.singolaRisposta(this.idInterview, this.idDomande[0]).subscribe({
      next: (r: any) => {
        console.log(r)
        this.titolo = r.title
        this.descrizione = r.description
        this.risposta = r.answer

        this.id = this.idDomande.shift()
        console.log(this.idDomande)

        this.idDomandePrecendenti.unshift(this.id)
      },
      error: (err) => {
        console.error(err)
      }
    })
  }

  ultimaDomanda() {
    if (this.idDomande.length === 0) {
      return true
    } else {
      return false
    }
  }

  indietro() {
    console.log(this.nomeCandidato)
    if (this.idDomandePrecendenti.length === 1) {
      this.router.navigate(['/home/test-domanda'], { state: { idTest: this.idInterview, email: this.nomeCandidato } })
    } else {
      this.id = this.idDomandePrecendenti.shift()
      this.idDomande.unshift(this.id)

      this.servizioTest.singolaRisposta(this.idInterview, this.idDomandePrecendenti[0]).subscribe({
        next: (r: any) => {
          this.titolo = r.title
          this.descrizione = r.description
          this.risposta = r.answer
        },
        error: (err) => {
          console.error(err)
        }
      })
    }
  }

  avanti() {
    this.servizioTest.singolaRisposta(this.idInterview, this.idDomande[0]).subscribe({
      next: (r: any) => {
        this.titolo = r.title
        this.descrizione = r.description
        this.risposta = r.answer

        this.id = this.idDomande.shift()

        this.idDomandePrecendenti.unshift(this.id)

      },
      error: (err) => {
        console.error(err)
      }
    })
  }

  concludi() {
    this.router.navigate(['/home/test-domanda'], { state: { idTest: this.idInterview, email: this.nomeCandidato } })
  }

}
