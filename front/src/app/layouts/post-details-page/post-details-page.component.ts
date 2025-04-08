import {Component, OnInit} from '@angular/core';
import {PostDetailsComponent} from '../../features/posts/components/post-details/post-details.component';
import {Button} from 'primeng/button';
import {ActivatedRoute, Router} from '@angular/router';
import {CommentsListComponent} from '../../features/comments/components/comments-list/comments-list.component';
import {CommentFormComponent} from '../../features/comments/components/comment-form/comment-form.component';
import {AuthService} from '../../features/auth/services/auth.service';

@Component({
  selector: 'app-post-details-page',
  imports: [
    PostDetailsComponent,
    Button,
    CommentsListComponent,
    CommentFormComponent
  ],
  templateUrl: './post-details-page.component.html',
  styleUrl: './post-details-page.component.scss'
})
export class PostDetailsPageComponent implements OnInit {

  postId!: number;
  authorId: number;

  constructor(private readonly router: Router,
              private readonly activatedRoute: ActivatedRoute,
              private readonly authService: AuthService) {
    this.authorId = this.authService.getLoggedUserId();
  }


  ngOnInit(): void {
    this.postId = this.activatedRoute.snapshot.params['id'];
  }

  goBack(): void {
    this.router.navigate(['/user-posts-feed'])
      .catch(console.error);
  }
}
