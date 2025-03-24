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

  public getAllTopic(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${environment.apiUrl}/topics`);
  }
}
