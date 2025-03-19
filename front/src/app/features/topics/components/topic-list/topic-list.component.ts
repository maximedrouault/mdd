import {Component, OnInit} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Topic} from '../../interfaces/topic.interface';
import {TopicsService} from '../../services/topics.service';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-topic-list',
  imports: [
    NgForOf,
    AsyncPipe,
    NgIf
  ],
  templateUrl: './topic-list.component.html',
  styleUrl: './topic-list.component.scss'
})
export class TopicListComponent implements OnInit{

  topics$: Observable<Topic[]> = of();

  constructor(private topicService: TopicsService) {}

  ngOnInit(): void {
    this.topics$ = this.topicService.getAllTopic();
  }
}
