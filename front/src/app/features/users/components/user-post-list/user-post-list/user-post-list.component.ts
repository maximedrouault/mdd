import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Post} from '../../../../posts/interfaces/post.interface';
import {UsersService} from '../../../service/users.service';

@Component({
  selector: 'app-user-post-list',
  imports: [],
  templateUrl: './user-post-list.component.html',
  styleUrl: './user-post-list.component.scss'
})
export class UserPostListComponent implements OnInit {

  posts$: Observable<Post[]> = of();
  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented

  constructor(private readonly usersService: UsersService) {}

  ngOnInit(): void {
    this.posts$ = this.usersService.getSubscribedPosts(this.userId);

    this.posts$.subscribe(console.log)
  };
}
