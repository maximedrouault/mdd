import { Routes } from '@angular/router';
import { TopicListComponent } from './features/topics/components/topic-list/topic-list.component';
import {TopicDetailsComponent} from './features/topics/components/topic-details/topic-details.component';
import {UserTopicListComponent} from './features/users/components/user-topic-list/user-topic-list.component';
import {UserProfilComponent} from './features/users/components/user-profile/user-profil/user-profil.component';
import {PostDetailsComponent} from './features/posts/components/post-details/post-details/post-details.component';
import {
  UserPostListComponent
} from './features/users/components/user-post-list/user-post-list/user-post-list.component';

export const routes: Routes = [
  { path: 'topic-list', component: TopicListComponent },
  { path: 'topic-details', component: TopicDetailsComponent }, // TODO: Remove this line when the component is ready
  { path: 'user-topic-list', component: UserTopicListComponent }, // TODO: Remove this line when the component is ready
  { path: 'user-post-list', component: UserPostListComponent},
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'post-details', component: PostDetailsComponent }, // TODO: Remove this line when the component is ready
  { path: '**', redirectTo: '' }
];
