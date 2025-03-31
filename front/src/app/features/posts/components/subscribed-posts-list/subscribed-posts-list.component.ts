import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {map, Observable, of} from 'rxjs';
import {Post} from '../../interfaces/post.interface';
import {PostCardComponent} from '../post-card/post-card.component';
import {AsyncPipe} from '@angular/common';
import {Router} from '@angular/router';
import {PostsService} from '../../services/posts.service';

@Component({
  selector: 'app-subscribed-posts-list',
  imports: [
    PostCardComponent,
    AsyncPipe
  ],
  templateUrl: './subscribed-posts-list.component.html',
  styleUrl: './subscribed-posts-list.component.scss'
})
export class SubscribedPostsListComponent implements OnInit, OnChanges {

  subscribedPosts$: Observable<Post[]> = of();
  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented
  @Input() sortOrderDesc!: boolean;

  constructor(private readonly postsService: PostsService,
              private readonly router: Router) {}


  ngOnInit(): void {
    this.subscribedPosts$ = this.postsService.getSubscribedPosts(this.userId);
    this.sortSubscribedPosts();
  };

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['sortOrderDesc']) {
      this.sortSubscribedPosts();
    }
  }

  navigateToPostDetails(postId: number): void {
    this.router.navigate(['/post-details', postId])
      .catch(console.error);
  }

  private sortSubscribedPosts(): void {
    this.subscribedPosts$ = this.subscribedPosts$.pipe(
      map(posts => posts.sort((a, b) => this.sortOrderDesc
        ? new Date(b.creationDate).getTime() - new Date(a.creationDate).getTime()
        : new Date(a.creationDate).getTime() - new Date(b.creationDate).getTime()
      ))
    );
  }
}
