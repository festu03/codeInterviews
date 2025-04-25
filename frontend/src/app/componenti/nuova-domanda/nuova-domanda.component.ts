import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, ElementRef, ViewChild } from '@angular/core';

import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';

import Editor from '@toast-ui/editor';

import { DomandaService } from '../../servizi/domanda.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-nuova-domanda',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './nuova-domanda.component.html',
  styleUrl: './nuova-domanda.component.css'
})
export class NuovaDomandaComponent {

  editorDomanda: string;

  private editor!: Editor

  nuovaDomandaForm: FormGroup

  constructor(private changeDetector: ChangeDetectorRef, private servizioDomanda: DomandaService, private dialog: MatDialog) { }

  @ViewChild('editor', { static: false }) editorElement!: ElementRef

  ngOnInit() {
    this.nuovaDomandaForm = new FormGroup({
      title: new FormControl(null, Validators.required),
      typology: new FormControl(),
      difficulty: new FormControl(1, Validators.required),
      description: new FormControl(null, Validators.required),
    })
  }

  ngAfterViewInit() {
    this.editor = new Editor({
      el: this.editorElement.nativeElement,
      height: '250px',
      initialEditType: 'markdown',
      previewStyle: 'vertical',
      usageStatistics: false,
    })
    this.editor.on('change', () => {
      this.editorDomanda = this.editor.getMarkdown().trim()
      this.nuovaDomandaForm.controls['description'].setValue(this.editorDomanda)
      this.changeDetector.detectChanges()
    })
  }

  onSubmit() {

    const difficolta: number = Number(this.nuovaDomandaForm.value.difficulty)
    this.servizioDomanda.nuovaDomanda({
      title: this.nuovaDomandaForm.value.title,
      typology: this.nuovaDomandaForm.value.typology,
      difficulty: difficolta,
      description: this.nuovaDomandaForm.value.description,
    }).subscribe({
      error: () => {
        console.error("Domanda non inserita")
      },
      complete: () => {
        this.openDialog()
      }
    })
  }

  openDialog() {
    this.dialog.open(DialogComponent);
  }

}
