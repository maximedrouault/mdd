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
/**
 * Service for handling post-related operations.
 */
export class PostsService {

  constructor(private readonly http: HttpClient) { }

  /**
   * Retrieve all posts the user is subscribed to.
   *
   * @returns An observable array of subscribed posts.
   */
  public getSubscribedPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts/subscribed`);
  }

  /**
   * Retrieve detailed information of a specific post.
   *
   * @param postId - The ID of the post.
   * @returns An observable containing the post details.
   */
  public getPostDetails(postId: number): Observable<PostDetails> {
    return this.http.get<PostDetails>(`${environment.apiUrl}/posts/${postId}`);
  }

  /**
   * Save a new post.
   *
   * @param postToAdd - The post payload to add.
   * @returns An observable notifying when the post has been saved.
   */
  public savePost(postToAdd: PostPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/posts`, postToAdd);
  }
}

