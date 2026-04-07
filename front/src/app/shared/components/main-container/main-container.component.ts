import {Component, input} from '@angular/core';

@Component({
  selector: 'app-main-container',
  imports: [],
  templateUrl: './main-container.component.html',
  styleUrl: './main-container.component.scss',
  standalone: true
})
export class MainContainerComponent {
  center = input<boolean>(true);

}
