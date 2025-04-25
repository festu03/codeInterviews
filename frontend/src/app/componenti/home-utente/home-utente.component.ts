import { Component, ViewChild } from '@angular/core';
import { TestService } from '../../servizi/test.service';
import { CommonModule } from '@angular/common';

import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-utente',
  standalone: true,
  imports: [
    CommonModule,
    MatTable,
    MatTableModule,
    MatPaginator,
    MatPaginatorModule,
  ],
  templateUrl: './home-utente.component.html',
  styleUrl: './home-utente.component.css'
})
export class HomeUtenteComponent {

  colonne: string[] = ['titolo', 'email']

  testCandidato: any = []
  testCandidatoTabella = new MatTableDataSource<any>([])
  totDomande = 0

  paginaAttuale = 0
  elementiPagina = 10

  _test: any 

  constructor(private servizioTest: TestService, private router: Router) { }

  @ViewChild(MatPaginator) paginator: MatPaginator

  ngOnInit() {
    this.listaTest()
  }

  ngAfterViewInit() {
    if (this.paginator) {
      this.paginator.page.subscribe(() => {
        this.listaTest()
      })
    }
  }

  cambioPagina(event: PageEvent) {
    this.paginaAttuale = event.pageIndex
    this.elementiPagina = event.pageSize
    this.listaTest()
  }

  listaTest() {
    this.servizioTest.listaTestCandididato(this.paginaAttuale, this.elementiPagina).subscribe({
      next: (test: any) => {
        this.testCandidato = test.content
        this.testCandidatoTabella.data = this.testCandidato
        this.totDomande = test.totalElements
      },
      error: (err) => {
        console.error(err)
      }
    })

    if (this.paginator) {
      this.paginator.length = this.totDomande
      this.paginator.pageIndex = this.paginaAttuale
    }
  }

  test(id: number) {
    this._test = this.testCandidato.map((t: any) => ({
      nomeCandidato: t.email,
      id: id
    }))
    console.log(this._test)
    this.router.navigate(['/home/test-domanda'], { state: { test: this._test } })
  }
}
