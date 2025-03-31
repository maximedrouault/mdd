import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from '../../../../environments/environment.development';
import {HttpClient} from '@angular/common/http';
import {Post} from '../../posts/interfaces/post.interface';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private readonly http: HttpClient) {}


  public getSubscribedPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/user/${userId}/subscribed-posts`);
  }
}

