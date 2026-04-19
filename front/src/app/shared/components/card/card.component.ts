import {Component, ContentChild, ElementRef, input} from '@angular/core';
import {
  MatCard,
  MatCardActions,
  MatCardContent,
  MatCardSubtitle,
  MatCardTitle
} from "@angular/material/card";

@Component({
  selector: 'app-card',
  imports: [
    MatCard,
    MatCardContent,
    MatCardActions,
    MatCardTitle,
    MatCardSubtitle
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss',
  standalone: true
})
export class CardComponent {
  fullWidth = input<boolean>(false);
  title = input<string|null>(null);

  @ContentChild('subtitle') subtitleRef?: ElementRef;
  @ContentChild('actions') actionsRef?: ElementRef;

  get hasSubtitle() {
    return !!this.subtitleRef;
  }

  get hasActions() {
    return !!this.actionsRef;
  }

}
