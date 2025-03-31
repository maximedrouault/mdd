import {Component, OnInit} from '@angular/core';
import {Observable, of, switchMap} from 'rxjs';
import {Topic} from '../../../topics/interfaces/topic.interface';
import {TopicCardComponent} from '../../../topics/components/topic-card/topic-card.component';
import {AsyncPipe} from '@angular/common';
import {TopicsService} from '../../../topics/services/topics.service';

@Component({
  selector: 'app-user-topic-list',
  imports: [
    TopicCardComponent,
    AsyncPipe
  ],
  templateUrl: './user-topic-list.component.html',
  styleUrl: './user-topic-list.component.scss'
})
export class UserTopicListComponent implements OnInit {

  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented
  subscribedTopics$: Observable<Topic[]> = of();
  isSubscriptionPage: boolean = false;

  constructor(private readonly topicService: TopicsService) {}


  ngOnInit(): void {
    this.subscribedTopics$ = this.loadSubscribedTopics();
  };

  handleSubscription(topicId: number): void {
    this.subscribedTopics$ = this.topicService.deleteTopicSubscription(topicId, this.userId).pipe(
      switchMap(() => this.loadSubscribedTopics())
    );
  };

  private loadSubscribedTopics(): Observable<Topic[]> {
    return this.topicService.getSubscribedTopics(this.userId);
  }
}
