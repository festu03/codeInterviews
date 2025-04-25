import { Component, ViewChild } from '@angular/core';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { MatPaginator, MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTable, MatTableDataSource, MatTableModule } from '@angular/material/table';

import { CdkDrag, CdkDragDrop, CdkDropList, moveItemInArray } from '@angular/cdk/drag-drop';
import { DomandaService } from '../../servizi/domanda.service';

@Component({
  selector: 'app-nuovo-test',
  standalone: true,
  imports: [
    RouterModule,
    CommonModule,
    FormsModule,
    MatTable,
    MatPaginatorModule,
    MatTableModule,
    CdkDrag,
    CdkDropList,
  ],
  templateUrl: './nuovo-test.component.html',
  styleUrl: './nuovo-test.component.css'
})
export class NuovoTestComponent {

  titoloTest: string = ''

  colonne: string[] = ['lista', 'titolo', 'tipologia', 'difficolta']

  domande: any = []
  domandeTabella = new MatTableDataSource<any>([])
  totDomande: number = 0

  domandeSelezionate: any = []
  domandeTabellaSelezionate = new MatTableDataSource<any>([])

  domandeFinali: any = []

  paginaAttuale = 0
  elementiPagina = 5

  constructor(private servizioDomanda: DomandaService, private router: Router) { }

  @ViewChild(MatPaginator) paginator!: MatPaginator

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
      next: (domanda: any) => {
        console.log(domanda.content)
        this.domande = domanda.content
        this.domande = this.domande.map((d: any) => ({
          id: d.id,
          titolo: d.title,
          tipologia: d.typology,
          difficolta: d.difficulty,
        }))
        this.totDomande = domanda.totalElements

        this.domande.forEach((domanda: any, index: any) => {
          domanda.pagina = this.paginaAttuale
          domanda.posizione = index
          domanda.ordineFinale = null
        })

        this.domande = this.domande.filter((d: any) =>
          !this.domandeSelezionate.some((sel: any) => sel.id === d.id)
        )

        this.domandeTabella.data = this.domande

        if (this.paginator) {
          this.paginator.length = this.totDomande
          this.paginator.pageIndex = this.paginaAttuale
        }
      }
    })
  }

  selezionaDomanda(domanda: any) {
    let selezione = 0
    if (domanda.selezionata) {
      this.domande = this.domande.filter((d: any) => d.id !== domanda.id)

      domanda.ordineFinale = ++selezione
      this.domandeSelezionate.push(domanda)

      this.domandeSelezionate.sort((a: any, b: any) => a.ordineFinale - b.ordineFinale)

    } else {
      this.domandeSelezionate = this.domandeSelezionate.filter((d: any) => d.id !== domanda.id)
      domanda.ordineFinale = null

      if (domanda.pagina === this.paginaAttuale) {
        this.domande.push(domanda)
        this.domande.sort((a: any, b: any) => a.posizione - b.posizione)
      }

      this.domande = this.domande.filter((d: any) =>
        !this.domandeSelezionate.some((sel: any) => sel.id === d.id)
      )
    }

    this.domandeTabella.data = [...this.domande]
    this.domandeTabellaSelezionate.data = [...this.domandeSelezionate]
  }


  get domandaSelezionata() {
    return this.domandeSelezionate.length > 0
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.domandeSelezionate, event.previousIndex, event.currentIndex)
    this.domandeTabellaSelezionate.data = [...this.domandeSelezionate]
  }

  avanti() {
    this.domandeFinali = this.domandeTabellaSelezionate.data.map((domanda: any) => ({
      id: domanda.id,
      titolo: domanda.titolo,
      tipologia: domanda.tipologia,
      difficolta: domanda.difficolta
    }))
    this.router.navigate(['home/candidato'], { state: { titolo: this.titoloTest, domande: this.domandeFinali } })
  }
}
