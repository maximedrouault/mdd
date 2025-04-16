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
export class AuthService {

  constructor(private readonly http: HttpClient,
              private readonly router: Router) {}


  public getAuthToken(loginPayload: LoginPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/login`, loginPayload).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
      })
    );
  }

  public registerUser(registerPayload: RegisterPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/register`, registerPayload);
  }

  public logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login-choice'])
      .catch(console.error);
  }

  public getUserInfos(): Observable<UserInfos> {
    return this.http.get<UserInfos>(`${environment.apiUrl}/users`);
  }

  public updateUserInfos(userInfos: UserEditPayload): Observable<UserEditPayload> {
    return this.http.put<UserEditPayload>(`${environment.apiUrl}/auth/update`, userInfos);
  }
}
