import {Component} from '@angular/core';
import {
  SubscribedTopicsListComponent
} from '../../../features/topics/components/subscribed-topics-list/subscribed-topics-list.component';
import {UserEditComponent} from '../../../features/auth/components/user-edit/user-edit.component';

@Component({
  selector: 'app-user-profil',
  imports: [
    SubscribedTopicsListComponent,
    UserEditComponent
  ],
  templateUrl: './user-profil.component.html',
  styleUrl: './user-profil.component.scss'
})
export class UserProfilComponent {
}
