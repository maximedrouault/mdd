import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {PostDetails} from '../../interfaces/postDetails.interface';
import {PostsService} from '../../services/posts.service';
import {ActivatedRoute} from '@angular/router';
import {AsyncPipe, DatePipe, TitleCasePipe} from '@angular/common';

@Component({
  selector: 'app-post-details',
  imports: [
    AsyncPipe,
    DatePipe,
    TitleCasePipe
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent implements OnInit {

  constructor(private readonly postService: PostsService,
              private activatedRoute: ActivatedRoute) {}

  postId!: number;
  postDetails$: Observable<PostDetails> = of();

  ngOnInit(): void {
    this.postId = this.activatedRoute.snapshot.params['id'];

    this.postDetails$ = this.postService.getPostDetails(this.postId);
  }
}
