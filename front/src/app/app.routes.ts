import {Routes} from '@angular/router';
import {TopicListComponent} from './features/topics/components/topic-list/topic-list.component';
import {UserProfilComponent} from './layouts/user-profil/components/user-profil.component';
import {UserPostFeedComponent} from './layouts/user-post-feed/components/user-post-feed.component';
import {PostCardComponent} from './features/posts/components/post-card/post-card.component';

export const routes: Routes = [
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'topic-list', component: TopicListComponent },
  { path: 'user-post-feed', component: UserPostFeedComponent },
  { path: 'post-details/:id' , component: PostCardComponent },
  { path: '**', redirectTo: '' }
];
