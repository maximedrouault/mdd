import { Routes } from '@angular/router';
import { TopicListComponent } from './features/topics/components/topic-list/topic-list.component';
import {TopicSubscribeComponent} from './features/topics/components/topic-subscribe/topic-subscribe.component';

export const routes: Routes = [
  { path: 'topic-list', component: TopicListComponent },
  { path: 'topic-details', component: TopicSubscribeComponent },
  { path: '**', redirectTo: '' }
];
