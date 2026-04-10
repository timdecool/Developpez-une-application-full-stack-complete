import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {ArticleService} from "../../shared/services/article.service";
import {Article} from "../../shared/models/Article";
import {MatCard, MatCardContent, MatCardHeader, MatCardSubtitle, MatCardTitle} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {DatePipe} from "@angular/common";
import {Router, RouterLink} from "@angular/router";
import {MainContainerComponent} from "../../shared/components/main-container/main-container.component";
import {CardListComponent} from "../../shared/components/card-list/card-list.component";
import {CardComponent} from "../../shared/components/card/card.component";

@Component({
  selector: 'app-articles',
  imports: [
    HeaderComponent,
    MatButton,
    DatePipe,
    RouterLink,
    MainContainerComponent,
    CardListComponent,
    CardComponent
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
