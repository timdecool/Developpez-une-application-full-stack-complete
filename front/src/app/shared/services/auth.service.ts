import {inject, Injectable, signal} from '@angular/core';
import {ApiService} from "./api.service";
import {catchError, Observable, tap} from "rxjs";
import {AuthResponse} from "../models/AuthResponse";
import {UserRequest} from "../models/UserRequest";
import {User} from "../models/User";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  api = inject(ApiService);
  router = inject(Router);
  private token = signal<String|null>(null);
  private user = signal<User|null>(null);

  register(userRequest: UserRequest): Observable<AuthResponse> {
    console.log("registration")
    return this.api.post<AuthResponse>('auth/register', userRequest).pipe(
      tap(response => {
        this.token.set(response.token);
        this.user.set(response.user);
        this.router.navigate(['/dashboard']);
      })
    );
  }


}
