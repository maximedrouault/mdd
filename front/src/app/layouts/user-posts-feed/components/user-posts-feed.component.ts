import {Component} from '@angular/core';
import {SubscribedPostsListComponent} from '../../../features/posts/components/subscribed-posts-list/subscribed-posts-list.component';
import {Button} from 'primeng/button';
import {ToggleButton} from 'primeng/togglebutton';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-user-posts-feed',
  imports: [
    SubscribedPostsListComponent,
    Button,
    ToggleButton,
    FormsModule
  ],
  templateUrl: './user-posts-feed.component.html',
  styleUrl: './user-posts-feed.component.scss'
})
export class UserPostsFeedComponent {

  userId: number = 3; //
  sortOrderDesc: boolean = true;
}
