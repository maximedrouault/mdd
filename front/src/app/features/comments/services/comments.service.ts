import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable, tap} from 'rxjs';
import {Comment} from '../interfaces/responses/comment.interface';
import {environment} from '../../../../environments/environment';
import {CommentPayload} from '../interfaces/requests/comment-payload.interface';

@Injectable({
  providedIn: 'root'
})
/**
 * Service for handling comments operations.
 */
export class CommentsService {

  private readonly commentsSubject: BehaviorSubject<Comment[]> = new BehaviorSubject<Comment[]>([]);
  comments$: Observable<Comment[]> = this.commentsSubject.asObservable();

  constructor(private readonly http: HttpClient) {}

  /**
   * Retrieve comments by post ID.
   *
   * @param postId - The ID of the post.
   */
  public getCommentsByPostId(postId: number): void {
    this.http.get<Comment[]>(`${environment.apiUrl}/comments/post/${postId}`)
      .subscribe(comments => this.commentsSubject.next(comments));
  }

  /**
   * Save a new comment and refresh the comments list.
   *
   * @param commentToAdd - The comment payload to add.
   * @returns An observable notifying when the operation is complete.
   */
  public saveComment(commentToAdd: CommentPayload): Observable<void> {
    return this.http.post<void>(`${environment.apiUrl}/comments/post`, commentToAdd).pipe(
      tap(() => this.getCommentsByPostId(commentToAdd.postId))
    );
  }
}

