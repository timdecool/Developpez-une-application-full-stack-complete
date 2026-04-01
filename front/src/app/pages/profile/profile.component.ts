import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {ThemeService} from "../../shared/services/theme.service";
import {Theme} from "../../shared/models/Theme";

@Component({
  selector: 'app-profile',
    imports: [HeaderComponent, MatButton, MatCard, MatCardActions, MatCardContent, MatCardTitle],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
  standalone: true
})
export class ProfileComponent {

  themeService = inject(ThemeService);
  themes = signal<Theme[]>([]);

  ngOnInit() {
    this.loadSubscribedThemes();
  }

  loadSubscribedThemes() {
    if (this.themes().length > 0) this.themes.set([]);
    this.themeService.getSubscribedThemes().subscribe(response => {
      this.themes.set(response);
    })
  }

  unsubscribeTheme(id: number): void {
    this.themeService.unsubscribeTheme(id).subscribe(() => {
      this.themes.update(
        themes => themes.filter(theme => theme.id !== id)
      );
    })
  }

}
