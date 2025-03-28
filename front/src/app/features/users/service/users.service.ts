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


  public saveTopicSubscription(userId: number, topicId: number): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/user/${userId}/subscribed-topics/${topicId}`, null);
  };

  public deleteTopicSubscription(userId: number, topicId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/user/${userId}/subscribed-topics/${topicId}`);
  };

  public getSubscribedPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/user/${userId}/subscribed-posts`);
  }
}

