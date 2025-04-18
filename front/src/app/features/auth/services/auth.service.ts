import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthToken} from '../interfaces/responses/auth-token';
import {LoginPayload} from '../interfaces/requests/login-payload.interface';
import {Observable, tap} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {RegisterPayload} from '../interfaces/requests/register-payload.interface';
import {Router} from '@angular/router';
import {UserInfos} from '../interfaces/responses/user-infos.interface';
import {UserEditPayload} from '../interfaces/requests/user-edit-payload.interface';

@Injectable({
  providedIn: 'root'
})
/**
 * AuthService handles authentication related HTTP requests.
 */
export class AuthService {

  constructor(private readonly http: HttpClient,
              private readonly router: Router) {}

  /**
   * Logs in the user and saves the authentication token in localStorage.
   * @param loginPayload - The login credentials.
   * @returns An Observable that emits an AuthToken.
   */
  public getAuthToken(loginPayload: LoginPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/login`, loginPayload).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  /**
   * Registers a new user.
   * @param registerPayload - The registration data.
   * @returns An Observable that emits an AuthToken.
   */
  public registerUser(registerPayload: RegisterPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/register`, registerPayload);
  }

  /**
   * Logs out the user by removing the authentication token and navigating to the login choice page.
   */
  public logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login-choice'])
      .catch(console.error);
  }

  /**
   * Retrieves user information.
   * @returns An Observable that emits UserInfos.
   */
  public getUserInfos(): Observable<UserInfos> {
    return this.http.get<UserInfos>(`${environment.apiUrl}/users`);
  }

  /**
   * Updates the user information.
   * @param userInfos - The updated user data.
   * @returns An Observable that emits the updated UserEditPayload.
   */
  public updateUserInfos(userInfos: UserEditPayload): Observable<UserEditPayload> {
    return this.http.put<UserEditPayload>(`${environment.apiUrl}/auth/update`, userInfos);
  }
}
