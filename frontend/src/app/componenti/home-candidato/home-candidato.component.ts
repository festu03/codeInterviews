import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { CandidatoService } from '../../servizi/candidato.service';

import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-home-candidato',
  standalone: true,
  imports: [
    MatTable,
    MatTableModule,
  ],
  templateUrl: './home-candidato.component.html',
  styleUrl: './home-candidato.component.css'
})
export class HomeCandidatoComponent {

  nomeCandidato: any

  titoloTest: string = ''

  colonne: string[] = ['titolo', 'tipologia', 'difficolta']

  domande: any = []
  domandeTabella = new MatTableDataSource<any>([])

  idDomande: any = []

  constructor(private servizioCandidato: CandidatoService, private router: Router) { }

  ngOnInit() {
    this.nomeCandidato = localStorage.getItem('nome candidato')
    this.servizioCandidato.domandeCandidato().subscribe({
      next: (test: any) => {
        this.titoloTest = test.title
        this.domande = test.questions
        this.domandeTabella.data = this.domande
      },
      error: (error) => {
        console.error(error)
      }
    })
  }

  iniziaTest() {
    this.domande.forEach((d: any) => {
      this.idDomande.push(d.id)
    })
    this.router.navigate(['home/test-candidato'], { state: { idDomande: this.idDomande } })
  }

}
