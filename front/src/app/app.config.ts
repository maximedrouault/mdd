import {ApplicationConfig, LOCALE_ID, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {providePrimeNG} from 'primeng/config';
import {MyPreset} from '../themePreset'
import {authInterceptor} from './features/auth/interceptors/auth.interceptor';
import {unAuthInterceptor} from './features/auth/interceptors/un-auth.interceptor';

/**
 * Angular Application Configuration.
 *
 * This configuration exports the appConfig object which defines the providers for the Angular application.
 * It includes:
 * - Zone change detection with event coalescing enabled.
 * - Router setup with defined application routes.
 * - HTTP client configured with authentication interceptors.
 * - Asynchronous animations support.
 * - PrimeNG settings with a custom theme preset, set in the themePreset.ts file.
 * - Locale configured to French (fr-FR).
 */
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([authInterceptor, unAuthInterceptor])
    ),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: MyPreset,
        options: {
          darkModeSelector: 'light',
        }
      }
    }),
    { provide: LOCALE_ID, useValue: 'fr-FR' }
  ],
};
