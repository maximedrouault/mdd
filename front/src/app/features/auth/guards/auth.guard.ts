import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {jwtDecode} from 'jwt-decode';

export const authGuard: CanActivateFn = (): boolean => {
  const token: string | null = localStorage.getItem('token');
  const router: Router = inject(Router);

  const redirectToLoginChoice: () => void = () => {
    router.navigate(['login-choice'])
      .catch(console.error);
  }

  if (!token) {
    redirectToLoginChoice();

    return false;
  }

  const tokenExpirationTime: number | undefined = jwtDecode(token).exp;
  const currentTime: number = Math.floor(Date.now() / 1000); // current time from milliseconds to seconds

  if (tokenExpirationTime && (tokenExpirationTime > currentTime)) {
    return true;
  } else {
    localStorage.removeItem('token');
    redirectToLoginChoice();

    return false;
  }
};
