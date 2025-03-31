import {Component, OnInit} from '@angular/core';
import {Observable, of, switchMap} from 'rxjs';
import {Topic} from '../../interfaces/topic.interface';
import {TopicCardComponent} from '../topic-card/topic-card.component';
import {AsyncPipe} from '@angular/common';
import {TopicsService} from '../../services/topics.service';

@Component({
  selector: 'app-subscribed-topics-list',
  imports: [
    TopicCardComponent,
    AsyncPipe
  ],
  templateUrl: './subscribed-topics-list.component.html',
  styleUrl: './subscribed-topics-list.component.scss'
})
export class SubscribedTopicsListComponent implements OnInit {

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
