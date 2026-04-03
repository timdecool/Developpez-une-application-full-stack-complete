import {Component, inject, signal} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {MatButton} from "@angular/material/button";
import {MatCard, MatCardActions, MatCardContent, MatCardTitle} from "@angular/material/card";
import {ThemeService} from "../../shared/services/theme.service";
import {Theme} from "../../shared/models/Theme";
import {AuthService} from "../../shared/services/auth.service";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatError, MatFormField, MatInput, MatLabel} from "@angular/material/input";
import {AuthRequest} from "../../shared/models/AuthRequest";
import {UserRequest} from "../../shared/models/UserRequest";

@Component({
  selector: 'app-profile',
  imports: [HeaderComponent, MatButton, MatCard, MatCardActions, MatCardContent, MatCardTitle, ReactiveFormsModule, MatError, MatFormField, MatInput, MatLabel],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
  standalone: true
})
export class ProfileComponent {

  themeService = inject(ThemeService);
  authService = inject(AuthService);
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

  profileForm = new FormGroup({
    username: new FormControl(this.authService.getUser()?.username, Validators.required),
    email: new FormControl(this.authService.getUser()?.email, [Validators.required, Validators.email]),
    password: new FormControl(null, [
      Validators.minLength(8),
      Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])/)
    ])
  })

  updateUser() {
    if (this.profileForm.invalid) return;
    this.authService.updateProfile(this.profileForm.value as UserRequest).subscribe();
  }

}
