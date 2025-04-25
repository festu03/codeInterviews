import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import { MatIconModule } from '@angular/material/icon';

import { AuthService } from '../../auth/auth.service';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,

  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  logo = 'https://web.liferay.com/it/web/pages-smc-treviso-s-r-l/profile?p_p_id=2_WAR_osbcorpprofileportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=serveMedia&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_count=1&_2_WAR_osbcorpprofileportlet_assetAttachmentId=223467698'

  constructor(private authService: AuthService, private router: Router) { }

  accessoUtente() {
    return this.authService.accessoUtente();
  }

  accessoCandidato() {
    return this.authService.accessoCandidato();
  }

  disconnessione() {
    this.authService.disconnessione()
    this.router.navigate(['/benvenuto'])
  }

  testCandidato() {
    return this.router.url.includes('/test-candidato');
  }

}
