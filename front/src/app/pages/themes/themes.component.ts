import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {ThemeService} from "../../shared/services/theme.service";
import {Theme} from "../../shared/models/Theme";
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {MatButton} from "@angular/material/button";
import {MainContainerComponent} from "../../shared/components/main-container/main-container.component";
import {CardListComponent} from "../../shared/components/card-list/card-list.component";
import {CardComponent} from "../../shared/components/card/card.component";

@Component({
  selector: 'app-themes',
  imports: [HeaderComponent, MatButton, MainContainerComponent, CardListComponent, CardComponent],
  templateUrl: './themes.component.html',
  styleUrl: './themes.component.scss',
  standalone: true
})
export class ThemesComponent {

  themeService = inject(ThemeService);

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

  subscribeTheme(id: number): void {
    this.themeService.subscribeTheme(id).subscribe(() => {
      this.themes.update(
        themes => themes.map(theme => theme.id === id ? { ...theme, subscribed: true }:theme)
      );
    })
  }

}
