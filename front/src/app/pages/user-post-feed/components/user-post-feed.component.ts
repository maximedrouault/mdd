import { Component } from '@angular/core';
import {UserPostListComponent} from '../../../features/users/components/user-post-list/user-post-list.component';

@Component({
  selector: 'app-user-post-feed',
  imports: [
    UserPostListComponent
  ],
  templateUrl: './user-post-feed.component.html',
  styleUrl: './user-post-feed.component.scss'
})
export class UserPostFeedComponent {

}
