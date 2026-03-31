import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";

@Component({
  selector: 'app-articles',
  imports: [HeaderComponent],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss',
  standalone: true
})
export class ArticlesComponent {

}
