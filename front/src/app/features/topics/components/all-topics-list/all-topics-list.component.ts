import {Component, OnInit} from '@angular/core';
import {map, Observable, of, switchMap} from 'rxjs';
import {Topic} from '../../interfaces/responses/topic.interface';
import {TopicsService} from '../../services/topics.service';
import {AsyncPipe} from '@angular/common';
import {TopicCardComponent} from '../topic-card/topic-card.component';
import {AuthService} from '../../../auth/services/auth.service';

@Component({
  selector: 'app-all-topics-list',
  imports: [
    AsyncPipe,
    TopicCardComponent
  ],
  templateUrl: './all-topics-list.component.html',
  styleUrl: './all-topics-list.component.scss'
})
export class AllTopicsListComponent implements OnInit{

  allTopics$: Observable<Topic[]> = of();
  loggedUserId: number;
  subscribedTopicIds: Set<number> = new Set<number>();
  isSubscriptionPage: boolean = true;

  constructor(private readonly topicService: TopicsService,
              private readonly authService: AuthService) {
    this.loggedUserId = this.authService.getLoggedUserId();
  }


  ngOnInit(): void {
    this.allTopics$ = this.topicService.getAllTopics();
    this.loadSubscribedTopicIds().subscribe(
      topicIds => this.subscribedTopicIds = topicIds
    );
  };


  isSubscribed(topicId: number): boolean {
    return this.subscribedTopicIds.has(topicId);
  };

  handleSubscription(topicId: number): void {
    this.topicService.saveTopicSubscription(topicId, this.loggedUserId).pipe(
      switchMap(() => this.loadSubscribedTopicIds())
    ).subscribe(topicIds => {
      this.subscribedTopicIds = topicIds;
    });
  }

  private loadSubscribedTopicIds(): Observable<Set<number>> {
    return this.topicService.getSubscribedTopics(this.loggedUserId).pipe(
      map(topics => new Set(topics.map(topic => topic.id)))
    );
  }
}
