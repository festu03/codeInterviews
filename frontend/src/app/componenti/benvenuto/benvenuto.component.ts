import { Component } from '@angular/core';

import { MarkdownModule } from 'ngx-markdown';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-benvenuto',
  standalone: true,
  imports: [
    MarkdownModule,
    RouterModule,
  ],
  templateUrl: './benvenuto.component.html',
  styleUrl: './benvenuto.component.css'
})
export class BenvenutoComponent {

  logo = 'https://web.liferay.com/it/web/pages-smc-treviso-s-r-l/profile?p_p_id=2_WAR_osbcorpprofileportlet&p_p_lifecycle=2&p_p_state=normal&p_p_mode=view&p_p_resource_id=serveMedia&p_p_cacheability=cacheLevelPage&p_p_col_id=column-1&p_p_col_count=1&_2_WAR_osbcorpprofileportlet_assetAttachmentId=223467698'

}
