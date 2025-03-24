import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Topic} from '../interfaces/topic.interface';
import {environment} from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class TopicsService {

  constructor(private readonly http: HttpClient) { }

  public getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics`);
  }

  public getSubscribedTopics(userId: number): Observable<Topic[]> {
    return of([
      {
        id: 1,
        name: 'Subscribed Topic 1',
        description: 'This is a subscribed topic'
      },
      {
        id: 5,
        name: 'Subscribed Topic 2',
        description: 'This is a subscribed topic'
      }
    ]);
  }
}
