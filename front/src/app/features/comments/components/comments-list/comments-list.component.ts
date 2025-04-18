import {Component, Input, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Comment} from '../../interfaces/responses/comment.interface';
import {CommentsService} from '../../services/comments.service';
import {AsyncPipe} from '@angular/common';
import {CommentCardComponent} from '../comment-card/comment-card.component';

@Component({
  selector: 'app-comments-list',
  imports: [
    AsyncPipe,
    CommentCardComponent
  ],
  templateUrl: './comments-list.component.html',
  styleUrl: './comments-list.component.scss'
})
export class CommentsListComponent implements OnInit {

  @Input() postId!: number;
  comments$: Observable<Comment[]> = of();

  constructor(private readonly commentsService: CommentsService) {}


  ngOnInit(): void {
    this.commentsService.getCommentsByPostId(this.postId);
    this.comments$ = this.commentsService.comments$;
  }
}
