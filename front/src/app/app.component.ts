import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from "@angular/router";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    imports: [
      RouterOutlet
    ],
    styleUrls: ['./app.component.scss'],
    standalone: true
})
export class AppComponent {
  title = 'front';
}
