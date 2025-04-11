import {Routes} from '@angular/router';
import {AllTopicsListComponent} from './features/topics/components/all-topics-list/all-topics-list.component';
import {UserProfilComponent} from './layouts/user-profil/components/user-profil.component';
import {UserPostsFeedComponent} from './layouts/user-posts-feed/components/user-posts-feed.component';
import {PostDetailsPageComponent} from './layouts/post-details-page/post-details-page.component';
import {PostFormComponent} from './features/posts/components/post-form/post-form.component';
import {UserLoginComponent} from './features/auth/components/user-login/user-login.component';
import {authGuard} from './features/auth/guards/auth.guard';
import {LoginChoiceComponent} from './features/auth/components/login-choice/login-choice.component';
import {UserRegisterComponent} from './features/auth/components/user-register/user-register.component';
import {HeaderWithoutMenuComponent} from './layouts/headers/header-without-menu/header-without-menu.component';
import {HeaderWithMenuComponent} from './layouts/headers/header-with-menu/header-with-menu.component';

export const routes: Routes = [
  { path: '', redirectTo: 'user-posts-feed', pathMatch: 'full' },
  { path: 'login-choice', component: LoginChoiceComponent },
  { path: '', component: HeaderWithoutMenuComponent,
    children: [
      { path: 'user-register', component: UserRegisterComponent },
      { path: 'user-login', component: UserLoginComponent }
    ]
  },
  { path: '', component: HeaderWithMenuComponent,
    children: [
      { path: 'user-profile', component: UserProfilComponent, canActivate: [authGuard] },
      { path: 'all-topics-list', component: AllTopicsListComponent, canActivate: [authGuard] },
      { path: 'user-posts-feed', component: UserPostsFeedComponent, canActivate: [authGuard] },
      { path: 'post-details/:id' , component: PostDetailsPageComponent, canActivate: [authGuard] },
      { path: 'post-form', component: PostFormComponent, canActivate: [authGuard] },
    ]
  },
  { path: '**', redirectTo: 'user-posts-feed' }
];
