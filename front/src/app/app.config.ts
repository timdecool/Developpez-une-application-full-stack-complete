import {APP_INITIALIZER, ApplicationConfig, inject, LOCALE_ID, provideAppInitializer} from '@angular/core';
import {provideRouter} from '@angular/router';
import {routes} from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {authInterceptor} from "./shared/interceptors/auth.interceptor";
import {registerLocaleData} from "@angular/common";
import localeFr from '@angular/common/locales/fr'
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from "@angular/material/form-field";
import {AuthService} from "./shared/services/auth.service";

registerLocaleData(localeFr);

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([authInterceptor])),
    { provide: LOCALE_ID, useValue: 'fr' },
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'outline', floatLabel: 'always' } },
    provideAppInitializer(() => {
      const authService = inject(AuthService);
      if (authService.getToken()) {
        return authService.loadUserFromToken();
      }
      return Promise.resolve();
    })
  ],
};
