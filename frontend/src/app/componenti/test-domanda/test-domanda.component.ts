import { Component } from '@angular/core';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';

import { Router, RouterModule } from '@angular/router';
import { TestService } from '../../servizi/test.service';

@Component({
  selector: 'app-test-domanda',
  standalone: true,
  imports: [
    RouterModule,
    MatTable,
    MatTableModule,
  ],
  templateUrl: './test-domanda.component.html',
  styleUrl: './test-domanda.component.css'
})
export class TestDomandaComponent {

  nomeCandidato: string = ''
  titoloTest: string = ''

  colonne: string[] = ['titolo', 'tipologia', 'difficolta']

  domande: [] = []
  domandeTabella = new MatTableDataSource<any>([])

  idTest: number
  idDomanda: number[] = []

  test: any = []

  constructor(private servizioTest: TestService, private router: Router) { }

  ngOnInit() {
    this.test = history.state.test
    if (this.test) {
      this.idTest = history.state.test[0].id
      this.nomeCandidato = this.test[0].nomeCandidato
    }
    else {
      this.idTest = history.state.idTest
      this.nomeCandidato = history.state.email
    }

    this.servizioTest.listaTestDomanda(this.idTest).subscribe({
      next: (d: any) => {
        console.log(d)
        this.titoloTest = d.title
        this.domande = d.questions
        this.idDomanda = this.domande.map((iq: any) => iq.id)
        console.log(this.idDomanda)
        this.domandeTabella.data = this.domande
      }
    })
  }

  risposta() {
    console.log(this.nomeCandidato)
    this.router.navigate(['/home/test-risposta'], { state: { idTest: this.idTest, idDomande: this.idDomanda, email: this.nomeCandidato } })
  }

}
