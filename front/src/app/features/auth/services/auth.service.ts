import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthToken} from '../interfaces/responses/auth-token';
import {LoginPayload} from '../interfaces/requests/login-payload.interface';
import {Observable, tap} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {jwtDecode} from 'jwt-decode';
import {CustomJwtPayload} from '../interfaces/responses/custom-jwt-payload';
import {RegisterPayload} from '../interfaces/requests/register-payload.interface';
import {Router} from '@angular/router';
import {UserInfos} from '../interfaces/responses/user-infos.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedUserId!: number;

  constructor(private readonly http: HttpClient,
              private readonly router: Router) {
      this.getUserIdFromToken();
  }


  public getAuthToken(loginPayload: LoginPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/login`, loginPayload).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        this.getUserIdFromToken();
      })
    );
  }

  public getLoggedUserId(): number {
    return this.loggedUserId;
  }

  public registerUser(registerPayload: RegisterPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/register`, registerPayload);
  }

  public logout(): void {
    localStorage.removeItem('token');
    this.loggedUserId = 0;
    this.router.navigate(['/login-choice'])
      .catch(console.error);
  }

  public getUserInfo(userId: number): Observable<UserInfos> {
    return this.http.get<UserInfos>(`${environment.apiUrl}/users/${userId}`);
  }


  private getUserIdFromToken(): void {
    const token: string | null = localStorage.getItem('token');

    if (token) {
      try {
        this.loggedUserId = jwtDecode<CustomJwtPayload>(token).userId;
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    }
  }
}
