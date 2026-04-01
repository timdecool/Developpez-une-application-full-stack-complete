import {inject, Injectable, signal} from '@angular/core';
import {ApiService} from "./api.service";
import {Observable, tap} from "rxjs";
import {Theme} from "../models/Theme";

@Injectable({
  providedIn: 'root',
})
export class ThemeService {

  api = inject(ApiService);
  themes = signal<Theme[]>([]);

  getThemes(): Observable<Theme[]> {
    return this.api.get<Theme[]>('themes')
  }

  getSubscribedThemes(): Observable<Theme[]> {
    return this.api.get<Theme[]>('themes/me')
  }

  subscribeTheme(id: number): Observable<void> {
    return this.api.post<void>(`themes/${id}/subscribe`, {});
  }

  unsubscribeTheme(id: number): Observable<void> {
    return this.api.delete<void>(`themes/${id}/unsubscribe`);
  }
}
