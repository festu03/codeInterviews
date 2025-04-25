import { Component, ViewChild } from '@angular/core';

import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { DomandaService } from '../../servizi/domanda.service';

@Component({
  selector: 'app-domanda',
  standalone: true,
  imports: [
    MatTable,
    MatTableModule,
    MatPaginator,
    MatPaginatorModule,
  ],
  templateUrl: './domanda.component.html',
  styleUrl: './domanda.component.css'
})
export class DomandaComponent {

  colonne = ['titolo', 'tipologia', 'difficolta']

  domande: any = []
  domandeTabella = new MatTableDataSource<any>([])
  totDomande = 0

  paginaAttuale = 0
  elementiPagina = 10

  constructor(private servizioDomanda: DomandaService) { }

  @ViewChild(MatPaginator) paginator: MatPaginator

  ngOnInit() {
    this.listaDomane()
  }

  ngAfterViewInit() {
    if (this.paginator) {
      this.paginator.page.subscribe(() => {
        this.listaDomane()
      })
    }
  }

  cambioPagina(event: PageEvent) {
    this.paginaAttuale = event.pageIndex
    this.elementiPagina = event.pageSize
    this.listaDomane()
  }

  listaDomane() {
    this.servizioDomanda.listaDomande(this.paginaAttuale, this.elementiPagina).subscribe({
      next: (d: any) => {
        this.domande = d.content
        this.domandeTabella.data = this.domande

        this.totDomande = d.totalElements

        if (this.paginator) {
          this.paginator.length = this.totDomande
          this.paginator.pageIndex = this.paginaAttuale
        }
      }
    })
  }
}
