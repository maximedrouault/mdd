import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import {Router} from '@angular/router';
import {catchError} from 'rxjs';

export const unAuthInterceptor: HttpInterceptorFn = (req, next) => {
  const router = inject(Router);

  return next(req).pipe(
    catchError((error) => {
      if (error.status === 401) {
        console.log(error);
        localStorage.removeItem('token');
        router.navigate(['user-login'])
          .catch(console.error);
      }
      throw error;
    })
  );
};
