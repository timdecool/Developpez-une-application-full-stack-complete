import {Component, HostBinding, inject, input} from '@angular/core';
import {Location} from "@angular/common";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";

@Component({
  selector: 'app-back-arrow',
  imports: [
    MatIcon,
    MatIconButton
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
