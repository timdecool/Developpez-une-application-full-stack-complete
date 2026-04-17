import {Component, HostBinding, inject, input} from '@angular/core';
import {Location} from "@angular/common";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-back-arrow',
  imports: [
    MatIcon
  ],
  templateUrl: './back-arrow.component.html',
  styleUrl: './back-arrow.component.scss',
  standalone: true,
  host: {
    '[class.static]': 'isStatic()'
  }
})
export class BackArrowComponent {

  location = inject(Location)
  isStatic = input<boolean>(true);

  back() {
    this.location.back();
  }

}
