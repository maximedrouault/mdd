import {Component, OnInit} from '@angular/core';
import {map, Observable, of} from 'rxjs';
import {Topic} from '../../interfaces/topic.interface';
import {TopicsService} from '../../services/topics.service';
import {AsyncPipe} from '@angular/common';
import {TopicDetailsComponent} from '../topic-details/topic-details.component';

@Component({
  selector: 'app-topic-list',
  imports: [
    AsyncPipe,
    TopicDetailsComponent
  ],
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss'
})
export class TopicListComponent implements OnInit{

  allTopics$: Observable<Topic[]> = of();
  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented
  subscribedTopics$: Observable<Topic[]> = of();
  subscribedTopicIds: Set<number> = new Set<number>();


  constructor(private readonly topicService: TopicsService) {}

  ngOnInit(): void {
    this.allTopics$ = this.topicService.getAllTopics();
    this.subscribedTopics$ = this.topicService.getSubscribedTopics(this.userId);

    this.loadSubscribedTopicIds();
  };

  isSubscribed(topicId: number): boolean {
    return this.subscribedTopicIds.has(topicId);
  };

  private loadSubscribedTopicIds(): void {
    this.subscribedTopics$.pipe(
      map(topics => new Set(topics.map(topic => topic.id)))
    ).subscribe(topicIds => {
      this.subscribedTopicIds = topicIds;
    });
  };
}
