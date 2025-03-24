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

  allTopics$: Observable<Topic[]> = of();
  userId: number = 1; // TODO: get the user id from the logged in user when the authentication is implemented
  subscribedTopics$: Observable<Topic[]> = of();

  constructor(private readonly topicService: TopicsService) {}

  ngOnInit(): void {
    this.allTopics$ = this.topicService.getAllTopics();
    this.subscribedTopics$ = this.topicService.getSubscribedTopics(this.userId);
  }

  isSubscribed(topicId: number): boolean {
    let isSubscribed: boolean = false;

    this.subscribedTopics$.subscribe((subscribedTopics: Topic[]): void => {
      isSubscribed = subscribedTopics.some((subscribedTopic: Topic): boolean => subscribedTopic.id === topicId);
    });

    return isSubscribed;
  };
}
