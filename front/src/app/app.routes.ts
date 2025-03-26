import { Routes } from '@angular/router';
import { TopicListComponent } from './features/topics/components/topic-list/topic-list.component';
import {TopicDetailsComponent} from './features/topics/components/topic-details/topic-details.component';

export const routes: Routes = [
  { path: 'topic-list', component: TopicListComponent },
  { path: 'topic-details', component: TopicDetailsComponent }, // TODO: Remove this line when the component is ready
  { path: '**', redirectTo: '' }
];
