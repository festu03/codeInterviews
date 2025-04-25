import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { routes } from './app.routes';


import { HttpClientModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { MarkdownModule } from 'ngx-markdown';
import { MonacoEditorModule } from 'ngx-monaco-editor-v2';
import { interceptorTokenInterceptor } from './interceptors/interceptor-token.interceptor';



export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideAnimationsAsync(),
    importProvidersFrom(
      HttpClientModule,
      MarkdownModule.forRoot(),
      MonacoEditorModule.forRoot(),
    ),
    provideHttpClient(
      withInterceptors([interceptorTokenInterceptor]),
    )
  ]
};
