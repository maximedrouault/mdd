import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Topic} from '../../interfaces/topic.interface';
import {TopicsService} from '../../services/topics.service';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {TopicDetailsComponent} from '../topic-details/topic-details.component';

@Component({
  selector: 'app-topic-list',
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    TopicDetailsComponent
  ],
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss'
})
export class TopicListComponent implements OnInit{

  topics$: Observable<Topic[]> = of();

  constructor(private readonly topicService: TopicsService) {}

  ngOnInit(): void {
    this.topics$ = this.topicService.getAllTopic();
  }
}
