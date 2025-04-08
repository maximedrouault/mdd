import {HttpEvent, HttpHandlerFn, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

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
