import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {Article} from "../../shared/models/Article";
import {ArticleService} from "../../shared/services/article.service";
import {ActivatedRoute} from "@angular/router";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-article-details',
  imports: [HeaderComponent, DatePipe],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss',
  standalone: true
})
export class ArticleDetailsComponent {

  articleService = inject(ArticleService);
  route = inject(ActivatedRoute);

  article = signal<Article|null>(null);

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.articleService.getArticle(id).subscribe(article => {
      this.article.set(article);
    })
  }
}

