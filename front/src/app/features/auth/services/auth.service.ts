import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthToken} from '../interfaces/responses/auth-token';
import {LoginPayload} from '../interfaces/requests/login-payload.interface';
import {Observable, tap} from 'rxjs';
import {environment} from '../../../../environments/environment';
import {jwtDecode} from 'jwt-decode';
import {CustomJwtPayload} from '../interfaces/responses/custom-jwt-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedUserId: number | undefined;

  constructor(private readonly http: HttpClient) {
    if (!this.loggedUserId) {
      this.loggedUserId = this.getUserIdFromToken();
    }
  }


  public getAuthToken(loginPayload: LoginPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/login`, loginPayload).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        this.loggedUserId = this.getUserIdFromToken()
      })
    );
  }

  public getLoggedUserId(): number | undefined {
    return this.loggedUserId;
  }

  private getUserIdFromToken(): number | undefined {
    const token: string | null = localStorage.getItem('token');

    try {
      return token ? jwtDecode<CustomJwtPayload>(token).userId : undefined;
    } catch (error) {
      console.error('Error decoding token:', error);

      return undefined;
    }
  }
}
