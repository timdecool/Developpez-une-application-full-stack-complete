import {Component, inject} from '@angular/core';
import {HeaderComponent} from "../../shared/components/header/header.component";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatError, MatFormField, MatInput, MatLabel} from "@angular/material/input";
import {MatButton, MatIconButton} from "@angular/material/button";
import {AuthService} from "../../shared/services/auth.service";
import {UserRequest} from "../../shared/models/UserRequest";

@Component({
  selector: 'app-register',
  imports: [
    HeaderComponent,
    ReactiveFormsModule,
    MatFormField,
    MatInput, MatLabel, MatIconButton, MatButton, MatError
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
  standalone: true
})
export class RegisterComponent {

  auth = inject(AuthService);

  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(8),
      Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])/)
    ])
  })

  register() {
    if (this.registerForm.invalid) return;
    this.auth.register(this.registerForm.value as UserRequest).subscribe();
  }

}
