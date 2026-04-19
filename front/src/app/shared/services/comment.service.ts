import {inject, Injectable} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable} from "rxjs";
import {CommentRequest} from "../models/CommentRequest";
import {Comment} from "../models/Comment";

@Injectable({
  providedIn: 'root',
})
export class CommentService {

  api = inject(ApiService);

  getComments(articleId: number) : Observable<Comment[]> {
    return this.api.get<Comment[]>(`comments/article/${articleId}`);
  }

  createComment(comment: CommentRequest): Observable<Comment> {
    return this.api.post<Comment>(`comments`, comment);
  }

}
