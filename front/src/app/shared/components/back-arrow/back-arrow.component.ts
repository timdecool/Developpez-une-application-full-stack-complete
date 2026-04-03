import {Component, inject} from '@angular/core';
import {Location} from "@angular/common";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-back-arrow',
  imports: [
    MatIcon
  ],
  templateUrl: './back-arrow.component.html',
  styleUrl: './back-arrow.component.scss',
  standalone: true
})
export class BackArrowComponent {

  location = inject(Location)

  back() {
    this.location.back();
  }

}
