import {Component} from '@angular/core';
import {UserPostListComponent} from '../../../features/users/components/user-post-list/user-post-list.component';
import {Button} from 'primeng/button';
import {ToggleButton} from 'primeng/togglebutton';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-user-post-feed',
  imports: [
    UserPostListComponent,
    Button,
    ToggleButton,
    FormsModule
  ],
  templateUrl: './user-post-feed.component.html',
  styleUrl: './user-post-feed.component.scss'
})
export class UserPostFeedComponent {

  sortOrderDesc: boolean = true;
}
