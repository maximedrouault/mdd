import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../interfaces/post.interface';
import {environment} from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private readonly http: HttpClient) { }


  public getSubscribedPosts(userId: number): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts/subscribed/${userId}`);
  }}
