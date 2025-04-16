import {HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

/**
 * Authentication interceptor.
 * Attaches a Bearer token from local storage to the HTTP request header if available.
 *
 * @param req - The outgoing HTTP request.
 * @param next - The next interceptor in the chain.
 * @returns The observable HTTP event.
 */
export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> => {
  const token: string | null = localStorage.getItem('token');

  if (!token) {
    return next(req);
  }

  const clonedRequest: HttpRequest<unknown> = req.clone({
    setHeaders: { Authorization: `Bearer ${token}`}
  });

  return next(clonedRequest);
};

