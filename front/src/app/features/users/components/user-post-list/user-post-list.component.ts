import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {map, Observable, of} from 'rxjs';
import {Post} from '../../../posts/interfaces/post.interface';
import {UsersService} from '../../service/users.service';
import {PostDetailsComponent} from '../../../posts/components/post-details/post-details.component';
import {AsyncPipe} from '@angular/common';

@Component({
  selector: 'app-user-post-list',
  imports: [
    PostDetailsComponent,
    AsyncPipe
  ],
  templateUrl: './user-post-list.component.html',
  styleUrl: './user-post-list.component.scss'
})
export class UserPostListComponent implements OnInit, OnChanges {

  subscribedPosts$: Observable<Post[]> = of();
  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented
  @Input() sortOrderDesc!: boolean;

  constructor(private readonly usersService: UsersService) {}


  ngOnInit(): void {
    this.subscribedPosts$ = this.usersService.getSubscribedPosts(this.userId);
    this.sortSubscribedPosts();
  };

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['sortOrderDesc']) {
      this.sortSubscribedPosts();
    }
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
