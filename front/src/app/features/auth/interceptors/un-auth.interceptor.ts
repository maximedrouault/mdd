import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {catchError} from 'rxjs';

/**
 * Unauthorized interceptor.
 * Catches HTTP 401 errors, removes the token from local storage, and redirects the user to the login page.
 *
 * @param req - The outgoing HTTP request.
 * @param next - The next interceptor in the chain.
 * @returns The observable HTTP event.
 */
export const unAuthInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      if (error.status === 401) {
        console.log(error);
        localStorage.removeItem('token');
        router.navigate(['login-choice'])
          .catch(console.error);
      }
      throw error;
    })
  );
};

