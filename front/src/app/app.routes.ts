import {Routes} from '@angular/router';
import {TopicListComponent} from './features/topics/components/topic-list/topic-list.component';
import {UserProfilComponent} from './pages/user-profil/components/user-profil.component';
import {UserPostFeedComponent} from './pages/user-post-feed/components/user-post-feed.component';

export const routes: Routes = [
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'topic-list', component: TopicListComponent },
  { path: 'user-post-feed', component: UserPostFeedComponent },
  { path: '**', redirectTo: '' }
];
