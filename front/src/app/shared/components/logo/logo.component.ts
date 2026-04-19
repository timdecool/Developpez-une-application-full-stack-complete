import {Component, input} from '@angular/core';
import {NgOptimizedImage} from "@angular/common";

@Component({
  selector: 'app-logo',
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './logo.component.html',
  styleUrl: './logo.component.scss',
  standalone: true
})
export class LogoComponent {

  size = input<"sm"|"lg">("lg");
  hiddenLg = input<boolean>(true);

}
