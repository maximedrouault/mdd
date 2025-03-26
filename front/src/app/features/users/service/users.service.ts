import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Topic} from '../../topics/interfaces/topic.interface';
import {environment} from '../../../../environments/environment.development';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  constructor(private readonly http: HttpClient) {}


  public getSubscribedTopics(userId: number): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/user/${userId}/subscribed-topics`);
  }

  public saveTopicSubscription(userId: number, topicId: number): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/user/${userId}/subscribed-topics/${topicId}`, null);
  }
}

