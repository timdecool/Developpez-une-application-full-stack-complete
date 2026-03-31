import {Component, inject} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {AuthService} from "../../shared/services/auth.service";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthRequest} from "../../shared/models/AuthRequest";
import {MatButton} from "@angular/material/button";
import {MatError, MatFormField, MatInput, MatLabel} from "@angular/material/input";

@Component({
  selector: 'app-login',
  imports: [HeaderComponent, FormsModule, MatButton, MatError, MatFormField, MatInput, MatLabel, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  standalone: true
})
export class LoginComponent {

  authService = inject(AuthService);

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  })

  login() {
    if(this.loginForm.invalid) return;
    this.authService.login(this.loginForm.value as AuthRequest).subscribe();
  }

}
