import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';
import {jwtDecode} from 'jwt-decode';

/**
 * Auth guard that protects routes requiring authentication.
 * It verifies the presence and validity (expiration) of a JWT token stored in localStorage.
 * If the token does not exist or is expired, it navigates to the 'login-choice' route.
 *
 * @returns {boolean} True if the token is valid and not expired, false otherwise.
 */
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
