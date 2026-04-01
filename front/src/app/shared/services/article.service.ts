import {inject, Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {Article} from "../models/Article";
import {ArticleRequest} from "../models/ArticleRequest";

@Injectable({
  providedIn: 'root',
})
export class ArticleService {

  api = inject(ApiService);

  getArticles(): Observable<Article[]> {
    return this.api.get<Article[]>('articles')
  }

  getArticle(id: number): Observable<Article> {
    return this.api.get<Article>(`articles/${id}`);
  }

  createArticle(article: ArticleRequest): Observable<Article> {
    return this.api.post<Article>(`articles`, article);
  }

}
