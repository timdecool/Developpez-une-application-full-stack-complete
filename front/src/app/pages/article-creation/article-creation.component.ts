import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {ArticleService} from "../../shared/services/article.service";
import {ArticleRequest} from "../../shared/models/ArticleRequest";
import {Router} from "@angular/router";
import {MatFormField, MatInput, MatLabel} from "@angular/material/input";
import {MatOption, MatSelect} from "@angular/material/select";
import {Theme} from "../../shared/models/Theme";
import {ThemeService} from "../../shared/services/theme.service";
import {MatButton} from "@angular/material/button";
import {BackArrowComponent} from "../../shared/components/back-arrow/back-arrow.component";
import {MainContainerComponent} from "../../shared/components/main-container/main-container.component";
import {CdkTextareaAutosize} from "@angular/cdk/text-field";

@Component({
  selector: 'app-article-creation',
  imports: [HeaderComponent, ReactiveFormsModule, MatFormField, MatSelect, MatOption, MatButton, MatInput, MatLabel, BackArrowComponent, MainContainerComponent, CdkTextareaAutosize],
  templateUrl: './article-creation.component.html',
  styleUrl: './article-creation.component.scss',
  standalone: true
})
export class ArticleCreationComponent {

  articleService = inject(ArticleService);
  themeService = inject(ThemeService);

  router = inject(Router);
  themes = signal<Theme[]>([]);

  ngOnInit() {
    this.loadThemes();
  }

  loadThemes(): void {
    if (this.themes().length > 0) this.themes.set([]);
    this.themeService.getThemes().subscribe(response => {
      this.themes.set(response);
    })
  }

  articleForm = new FormGroup({
    themeId: new FormControl(0, Validators.required),
    title: new FormControl('', Validators.required),
    content: new FormControl('', Validators.required),
  })

  createArticle() {
    if(this.articleForm.invalid) return;
    this.articleService.createArticle(this.articleForm.value as ArticleRequest).subscribe(() => {
      this.router.navigate(['/articles'])
    });
  }
}
