import {Routes} from '@angular/router';
import {AllTopicsListComponent} from './features/topics/components/all-topics-list/all-topics-list.component';
import {UserProfilComponent} from './layouts/user-profil/components/user-profil.component';
import {UserPostsFeedComponent} from './layouts/user-posts-feed/components/user-posts-feed.component';
import {PostCardComponent} from './features/posts/components/post-card/post-card.component';

export const routes: Routes = [
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'all-topics-list', component: AllTopicsListComponent },
  { path: 'user-post-feed', component: UserPostsFeedComponent },
  { path: 'post-details/:id' , component: PostCardComponent },
  { path: '**', redirectTo: '' }
];
