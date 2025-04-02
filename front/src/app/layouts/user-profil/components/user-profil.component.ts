import {Component} from '@angular/core';
import {
  SubscribedTopicsListComponent
} from '../../../features/topics/components/subscribed-topics-list/subscribed-topics-list.component';

@Component({
  selector: 'app-user-profil',
  imports: [
    SubscribedTopicsListComponent
  ],
  templateUrl: './user-profil.component.html',
  styleUrl: './user-profil.component.scss'
})
export class UserProfilComponent {

  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented

}
