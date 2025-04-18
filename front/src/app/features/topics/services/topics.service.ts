import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Topic} from '../interfaces/responses/topic.interface';
import {environment} from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
/**
 * Service for handling topic-related operations.
 */
export class TopicsService {

  constructor(private readonly http: HttpClient) { }

  /**
   * Retrieve all available topics.
   *
   * @returns An observable array of topics.
   */
  public getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics`);
  };

  /**
   * Retrieve topics the user is subscribed to.
   *
   * @returns An observable array of subscribed topics.
   */
  public getSubscribedTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics/subscribed`);
  };

  /**
   * Subscribe to a topic.
   *
   * @param topicId - The ID of the topic.
   * @returns An observable notifying when the subscription has been saved.
   */
  public saveTopicSubscription(topicId: number): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/topics/${topicId}/subscribed`, null);
  };

  /**
   * Unsubscribe from a topic.
   *
   * @param topicId - The ID of the topic.
   * @returns An observable notifying when the subscription has been deleted.
   */
  public deleteTopicSubscription(topicId: number): Observable<void> {
    return this.http.delete<void>(`${environment.apiUrl}/topics/${topicId}/subscribed`);
  };
}

