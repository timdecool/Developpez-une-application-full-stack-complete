import {inject, Injectable, signal} from '@angular/core';
import {ApiService} from "./api.service";
import {catchError, Observable, tap} from "rxjs";
import {AuthResponse} from "../models/AuthResponse";
import {UserRequest} from "../models/UserRequest";
import {User} from "../models/User";
import {Router} from "@angular/router";
import {AuthRequest} from "../models/AuthRequest";

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  api = inject(ApiService);
  router = inject(Router);

  private user = signal<User|null>(null);
  private tokenKey = 'auth_token';

  register(userRequest: UserRequest): Observable<AuthResponse> {
    return this.api.post<AuthResponse>('auth/register', userRequest).pipe(
      tap(response => {
        this.setToken(response.token);
        this.user.set(response.user);
        this.router.navigate(['/dashboard']);
      })
    );
  }

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.api.post<AuthResponse>('auth/login', authRequest).pipe(
      tap(response => {
        this.setToken(response.token);
        this.user.set(response.user);
        this.router.navigate(['/dashboard']);
      })
    );
  }

  setToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string|null {
    return localStorage.getItem(this.tokenKey);
  }

  removeToken() {
    localStorage.removeItem(this.tokenKey);
  }


}
