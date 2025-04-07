import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {jwtDecode} from 'jwt-decode';

export const authGuard: CanActivateFn = (): boolean => {
  const token: string | null = localStorage.getItem('token');
  const router: Router = inject(Router);

  if (!token) {
    router.navigate(['user-login'])
      .catch(console.error);

    return false;
  }

  const tokenExpirationTime: number | undefined = jwtDecode(token).exp;
  const currentTime: number = Math.floor(Date.now() / 1000); // current time from milliseconds to seconds

  if (tokenExpirationTime && tokenExpirationTime > currentTime) {
    return true;
  } else {
    localStorage.removeItem('token');
    router.navigate(['user-login'])
      .catch(console.error);

    return false;
  }
};
