import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Topic} from '../interfaces/topic.interface';
import {environment} from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private readonly http: HttpClient) { }


  public getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics`);
  };

  public getSubscribedTopics(userId: number): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics/subscribed/${userId}`);
  };

  public saveTopicSubscription(topicId: number, userId: number): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/topics/${topicId}/subscribed/${userId}`, null);
  };

  public deleteTopicSubscription(topicId: number, userId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/topics/${topicId}/subscribed/${userId}`);
  };
}
