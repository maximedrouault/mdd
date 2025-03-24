import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
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

  allTopics$: Observable<Topic[] | undefined> = of(undefined);
  userId: number = 3; // TODO: get the user id from the logged in user when the authentication is implemented
  subscribedTopicIds: Set<number> | null = null;

  constructor(private readonly topicService: TopicsService) {}

  ngOnInit(): void {
    this.allTopics$ = this.topicService.getAllTopics();
    this.topicService.getSubscribedTopics(this.userId).subscribe(
      (topics: Topic[]): void => {
      this.subscribedTopicIds = new Set(topics.map((topic: Topic): number => topic.id));
    });
  }

  isSubscribed(topicId: number): boolean | undefined {
    return this.subscribedTopicIds === null ? undefined : this.subscribedTopicIds.has(topicId);
  }
}
