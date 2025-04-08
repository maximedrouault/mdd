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
  userId: number | undefined = undefined;

  constructor(private readonly http: HttpClient) {
    this.userId = this.getUserIdFromToken();
  }


  public getAuthToken(loginPayload: LoginPayload): Observable<AuthToken> {
    return this.http.post<AuthToken>(`${environment.apiUrl}/auth/login`, loginPayload).pipe(
      tap(response => {
        localStorage.setItem('token', response.token);
        this.userId = this.getUserIdFromToken()
      })
    );
  }

  public getUserIdFromToken(): number | undefined {
    const token: string | null = localStorage.getItem('token');

    if (token) {
      return jwtDecode<CustomJwtPayload>(token).userId;
    } else {
      return undefined;
    }
  }
}
