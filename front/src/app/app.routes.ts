import {Routes} from '@angular/router';
import {AllTopicsListComponent} from './features/topics/components/all-topics-list/all-topics-list.component';
import {UserProfilComponent} from './layouts/user-profil/components/user-profil.component';
import {UserPostsFeedComponent} from './layouts/user-posts-feed/components/user-posts-feed.component';
import {PostDetailsPageComponent} from './layouts/post-details-page/post-details-page.component';
import {PostFormComponent} from './features/posts/components/post-form/post-form.component';

export const routes: Routes = [
  { path: 'user-profile', component: UserProfilComponent },
  { path: 'all-topics-list', component: AllTopicsListComponent },
  { path: 'user-posts-feed', component: UserPostsFeedComponent },
  { path: 'post-details/:id' , component: PostDetailsPageComponent },
  { path: 'post-form', component: PostFormComponent },
  { path: '**', redirectTo: '' }
];
