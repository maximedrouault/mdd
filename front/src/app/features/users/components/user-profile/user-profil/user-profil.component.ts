import { Component } from '@angular/core';
import {UserTopicListComponent} from '../../user-topic-list/user-topic-list.component';

@Component({
  selector: 'app-user-profil',
  imports: [
    UserTopicListComponent
  ],
  templateUrl: './user-profil.component.html',
  styleUrl: './user-profil.component.scss'
})
export class UserProfilComponent {

}
