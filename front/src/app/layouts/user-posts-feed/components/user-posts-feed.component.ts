import {Component} from '@angular/core';
import {SubscribedPostsListComponent} from '../../../features/posts/components/subscribed-posts-list/subscribed-posts-list.component';
import {Button} from 'primeng/button';
import {ToggleButton} from 'primeng/togglebutton';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from '../../../features/auth/services/auth.service';

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

  loggedUserId: number;
  sortOrderDesc: boolean = true;

  constructor(private readonly router: Router,
              private readonly authService: AuthService) {
    this.loggedUserId = this.authService.getLoggedUserId();
  }

  onAddPost(): void {
    this.router.navigate(['post-form'])
      .catch(console.error);
  }
}
