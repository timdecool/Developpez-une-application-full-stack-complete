import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {Article} from "../../shared/models/Article";
import {Comment} from "../../shared/models/Comment";
import {ArticleService} from "../../shared/services/article.service";
import {ActivatedRoute} from "@angular/router";
import {DatePipe} from "@angular/common";
import {CommentService} from "../../shared/services/comment.service";
import {MatFormField, MatInput} from "@angular/material/input";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {CommentRequest} from "../../shared/models/CommentRequest";
import {MatButton, MatIconButton} from "@angular/material/button";
import {BackArrowComponent} from "../../shared/components/back-arrow/back-arrow.component";
import {MainContainerComponent} from "../../shared/components/main-container/main-container.component";
import {CardComponent} from "../../shared/components/card/card.component";
import {MatIcon} from "@angular/material/icon";
import {CdkTextareaAutosize} from "@angular/cdk/text-field";

@Component({
  selector: 'app-article-details',
  imports: [HeaderComponent, DatePipe, MatFormField, MatInput, ReactiveFormsModule, MatButton, BackArrowComponent, MainContainerComponent, CardComponent, MatIcon, MatIconButton, CdkTextareaAutosize],
  templateUrl: './article-details.component.html',
  styleUrl: './article-details.component.scss',
  standalone: true
})
export class ArticleDetailsComponent {

  articleService = inject(ArticleService);
  commentService = inject(CommentService);
  route = inject(ActivatedRoute);

  article = signal<Article|null>(null);
  comments = signal<Comment[]>([]);

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.articleService.getArticle(id).subscribe(article => {
      this.article.set(article);
    })
    this.commentService.getComments(id).subscribe(comments => {
      this.comments.set(comments);
    })
  }

  commentForm = new FormGroup({
    content: new FormControl('', Validators.required)
  })

  createComment() {
    if (this.commentForm.invalid) return;
    this.commentService.createComment(
      {
        ...this.commentForm.value as CommentRequest,
        articleId: this.route.snapshot.params['id']
      }
    ).subscribe((response) => {
      this.comments.update(comments => [...comments, response])
    })
  }
}
