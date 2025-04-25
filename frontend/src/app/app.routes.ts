import { Routes } from '@angular/router';

import { authGuard, authGuardCandidato, authGuardUtente } from './auth/auth.guard';
import { BenvenutoComponent } from './componenti/benvenuto/benvenuto.component';
import { CandidatoTokenComponent } from './componenti/candidato-token/candidato-token.component';
import { CandidatoComponent } from './componenti/candidato/candidato.component';
import { DomandaComponent } from './componenti/domanda/domanda.component';
import { HomeCandidatoComponent } from './componenti/home-candidato/home-candidato.component';
import { HomeUtenteComponent } from './componenti/home-utente/home-utente.component';
import { HomeComponent } from './componenti/home/home.component';
import { LoginCandidatoComponent } from './componenti/login-candidato/login-candidato.component';
import { LoginUtenteComponent } from './componenti/login-utente/login-utente.component';
import { LogoutCandidatoComponent } from './componenti/logout-candidato/logout-candidato.component';
import { NuovaDomandaComponent } from './componenti/nuova-domanda/nuova-domanda.component';
import { NuovoTestComponent } from './componenti/nuovo-test/nuovo-test.component';
import { TestCandidatoComponent } from './componenti/test-candidato/test-candidato.component';
import { TestDomandaComponent } from './componenti/test-domanda/test-domanda.component';
import { TestRispostaComponent } from './componenti/test-risposta/test-risposta.component';

export const routes: Routes = [
    { path: 'benvenuto', component: BenvenutoComponent },
    { path: '', redirectTo: 'benvenuto', pathMatch: 'full' },

    { path: 'login-utente', component: LoginUtenteComponent },
    { path: 'login-candidato', component: LoginCandidatoComponent },

    {

        path: 'home', component: HomeComponent, canActivate: [authGuard], children: [
            { path: 'home-utente', component: HomeUtenteComponent, canActivate: [authGuardUtente] },
            { path: 'domanda', component: DomandaComponent, canActivate: [authGuardUtente] },
            { path: 'nuova-domanda', component: NuovaDomandaComponent, canActivate: [authGuardUtente] },
            { path: 'nuovo-test', component: NuovoTestComponent, canActivate: [authGuardUtente] },
            { path: 'candidato', component: CandidatoComponent, canActivate: [authGuardUtente] },
            { path: 'candidato-token', component: CandidatoTokenComponent, canActivate: [authGuardUtente] },
            { path: 'test-domanda', component: TestDomandaComponent, canActivate: [authGuardUtente] },
            { path: 'test-risposta', component: TestRispostaComponent, canActivate: [authGuardUtente] },

            { path: 'home-candidato', component: HomeCandidatoComponent, canActivate: [authGuardCandidato] },
            { path: 'test-candidato', component: TestCandidatoComponent, canActivate: [authGuardCandidato] },
            { path: 'logout-candidato', component: LogoutCandidatoComponent, canActivate: [authGuardCandidato] },
        ],
    },

    { path: '**', redirectTo: 'benvenuto' },

];
