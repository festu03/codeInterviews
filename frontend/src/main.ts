import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));


declare const require: any;

require.config({
  paths: {
    vs: '/assets/monaco/min/vs/editor'  // Assicurati che punti correttamente alla cartella editor
  }
})