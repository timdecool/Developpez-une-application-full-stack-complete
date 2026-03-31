import { Component } from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";

@Component({
  selector: 'app-article-details',
  imports: [HeaderComponent],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss',
  standalone: true
})
export class ArticleDetailsComponent {

}
