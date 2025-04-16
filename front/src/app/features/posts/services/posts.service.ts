import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Post} from '../interfaces/responses/post.interface';
import {PostDetails} from '../interfaces/responses/post-details.interface';
import {environment} from '../../../../environments/environment';
import {PostPayload} from '../interfaces/requests/post-payload-interface';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private readonly http: HttpClient) { }


  public getSubscribedPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts/subscribed`);
  }

  public getPostDetails(postId: number): Observable<PostDetails> {
    return this.http.get<PostDetails>(`${environment.apiUrl}/posts/${postId}`);
  }

  public savePost(postToAdd: PostPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/posts`, postToAdd);
  }
}
