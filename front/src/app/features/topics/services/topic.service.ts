import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Topic} from '../../../interfaces/topic';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(private http: HttpClient) { }

  public getAllTopic(): Observable<Topic[]> {
    return this.http.get<Topic[]>('http://localhost:9000/api/topics');
  }
}
