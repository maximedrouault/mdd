import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Comment} from '../interfaces/responses/comment.interface';
import {environment} from '../../../../environments/environment';
import {CommentPayload} from '../interfaces/requests/comment-payload.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  private readonly commentsSubject: BehaviorSubject<Comment[]> = new BehaviorSubject<Comment[]>([]);
  comments$: Observable<Comment[]> = this.commentsSubject.asObservable();

  constructor(private readonly http: HttpClient) {}


  public getCommentsByPostId(postId: number): void {
    this.http.get<Comment[]>(`${environment.apiUrl}/comments/post/${postId}`)
      .subscribe(comments => this.commentsSubject.next(comments));
  }

  public saveComment(commentToAdd: CommentPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/comments/post`, commentToAdd).pipe(
      tap(() => this.getCommentsByPostId(commentToAdd.postId))
    );
  }
}
