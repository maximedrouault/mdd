import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Comment} from '../interfaces/comment.interface';
import {environment} from '../../../../environments/environment';
import {CommentPayload} from '../interfaces/comment-payload.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private readonly commentsSubject: BehaviorSubject<Comment[]> = new BehaviorSubject<Comment[]>([]);
  comments$: Observable<Comment[]> = this.commentsSubject.asObservable();

  constructor(private readonly http: HttpClient) {}


  public getCommentsByPostId(postId: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${environment.apiUrl}/comments/post/${postId}`).pipe(
      tap(comments => this.commentsSubject.next(comments))
    );
  }

  public saveComment(commentPayload: CommentPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/comments/post`, commentPayload).pipe(
      tap(() => {
        this.getCommentsByPostId(commentPayload.postId).subscribe();
      })
    );
  }
}
