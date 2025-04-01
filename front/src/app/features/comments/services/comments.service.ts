import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Comment} from '../interfaces/comment.interface';
import {environment} from '../../../../environments/environment';
import {CommentPayload} from '../interfaces/comment-payload.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private readonly http: HttpClient) {}


  public getCommentsByPostId(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/post/${postId}`);
  }

  public saveComment(commentPayload: CommentPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/comments/post`, commentPayload);
  }
}
