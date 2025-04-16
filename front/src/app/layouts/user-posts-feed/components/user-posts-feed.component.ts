import {Component} from '@angular/core';
import {
  SubscribedPostsListComponent
} from '../../../features/posts/components/subscribed-posts-list/subscribed-posts-list.component';
import {Button} from 'primeng/button';
import {ToggleButton} from 'primeng/togglebutton';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';

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

  sortOrderDesc: boolean = true;

  constructor(private readonly router: Router) {};

  onAddPost(): void {
    this.router.navigate(['post-form'])
      .catch(console.error);
  }
}
