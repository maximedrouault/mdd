import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Topic} from '../interfaces/responses/topic.interface';
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private readonly http: HttpClient) { }


  public getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics`);
  };

  public getSubscribedTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics/subscribed`);
  };

  public saveTopicSubscription(topicId: number): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/topics/${topicId}/subscribed`, null);
  };

  public deleteTopicSubscription(topicId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/topics/${topicId}/subscribed`);
  };
}
