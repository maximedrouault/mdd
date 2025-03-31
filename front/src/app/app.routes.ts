import {Routes} from '@angular/router';
import {AllTopicsListComponent} from './features/topics/components/all-topics-list/all-topics-list.component';
import {UserProfilComponent} from './layouts/user-profil/components/user-profil.component';
import {UserPostsFeedComponent} from './layouts/user-posts-feed/components/user-posts-feed.component';
import {PostDetailsComponent} from './features/posts/components/post-details/post-details.component';

export const routes: Routes = [
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'all-topics-list', component: AllTopicsListComponent },
  { path: 'user-posts-feed', component: UserPostsFeedComponent },
  { path: 'post-details/:id' , component: PostDetailsComponent },
  { path: '**', redirectTo: '' }
];
