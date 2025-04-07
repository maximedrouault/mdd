import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const authGuard: CanActivateFn = (): boolean => {
  const token: string | null = localStorage.getItem('token');

  const router: Router = inject(Router);

  if (token) {
    return true;
  } else {
    router.navigate(['user-login'])
      .catch(console.error);
    return false;
  }
};
