import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {ArticleService} from "../../shared/services/article.service";
import {Article} from "../../shared/models/Article";
import {MatCard, MatCardContent, MatCardSubtitle, MatCardTitle} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {DatePipe} from "@angular/common";
import {Router, RouterLink} from "@angular/router";

@Component({
  selector: 'app-articles',
  imports: [
    HeaderComponent,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatButton,
    MatCardSubtitle,
    DatePipe,
    RouterLink
  ],
  templateUrl: './articles.component.html',
  styleUrl: './articles.component.scss',
  standalone: true
})
export class ArticlesComponent {

  articleService = inject(ArticleService);
  router = inject(Router)

  articles = signal<Article[]>([]);


  ngOnInit() {
    if (this.articles().length > 0) this.articles.set([]);
    this.articleService.getArticles().subscribe(articles => {
      this.articles.set(articles);
    });
  }

  readArticle(id: number): void {
    this.router.navigate([`/articles/${id}`]);
  }
}
