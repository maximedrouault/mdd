import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Comment} from '../../interfaces/comment.interface';
import {CommentsService} from '../../services/comments.service';
import {ActivatedRoute} from '@angular/router';
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

  comments$: Observable<Comment[]> = of();
  postId!: number;

  constructor(private readonly commentsService: CommentsService,
              private readonly activatedRoute: ActivatedRoute  ) {}


  ngOnInit(): void {
    this.postId = this.activatedRoute.snapshot.params['id'];

    this.comments$ = this.commentsService.getCommentsByPostId(this.postId);
  }
}
