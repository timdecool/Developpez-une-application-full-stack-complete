import {Component, input} from '@angular/core';

@Component({
  selector: 'app-form-container',
  imports: [],
  templateUrl: './form-container.component.html',
  styleUrl: './form-container.component.scss',
  standalone: true
})
export class FormContainerComponent {

  title = input<string>();
}
