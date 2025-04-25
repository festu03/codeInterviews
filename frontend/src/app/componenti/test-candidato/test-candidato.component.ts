import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

import { MarkdownModule } from 'ngx-markdown';
import { MonacoEditorModule } from 'ngx-monaco-editor-v2';

import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { CandidatoService } from '../../servizi/candidato.service';

@Component({
  selector: 'app-test-candidato',
  standalone: true,
  imports: [
    CommonModule,
    MarkdownModule,
    MonacoEditorModule,
    FormsModule,
    RouterModule,
  ],
  templateUrl: './test-candidato.component.html',
  styleUrl: './test-candidato.component.css'
})
export class TestCandidatoComponent {

  editorOptions = {}
  risposta: string = ''

  idDomande: number[] = []
  idSingolaDomanda: number

  titolo: string = ''
  descrizione: string = ''

  constructor(private servizioCandidato: CandidatoService, private router: Router) { }

  ngOnInit() {
    this.idDomande = history.state.idDomande
    this.idSingolaDomanda = this.idDomande[0]
    this.servizioCandidato.singolaDomanda(this.idSingolaDomanda).subscribe({
      next: (d: any) => {
        this.titolo = d[0].titolo
        this.descrizione = d[0].descrizione
        this.idDomande.shift()
        this.editorOptions = { theme: 'vs-dark', language: d[0].tipologia || 'txt' }
      },
      error: (error: any) => {
        console.error(error)
      }
    })
  }

  avanti() {
    this.servizioCandidato.singolaRisposta(this.idSingolaDomanda, {
      answerBody: this.risposta
    }).subscribe({
      error: (error) => {
        console.error(error)
      }
    })
    this.idSingolaDomanda = this.idDomande[0]

    this.servizioCandidato.singolaDomanda(this.idSingolaDomanda).subscribe({
      next: (d: any) => {
        console.log(d)
        this.titolo = d[0].titolo
        this.descrizione = d[0].descrizione
        this.idDomande.shift()
        this.risposta = ''
      },
      error: (error: any) => {
        console.error(error)
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

  concludi() {
    this.servizioCandidato.singolaRisposta(this.idSingolaDomanda, {
      answerBody: this.risposta
    }).subscribe({
      error: (error) => {
        console.error(error)
      },
      complete: () => {
        this.router.navigate(["/home/logout-candidato"])
      }
    })
  }
}

