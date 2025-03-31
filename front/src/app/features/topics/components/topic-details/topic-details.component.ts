import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Topic} from '../../interfaces/topic.interface';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {PrimeTemplate} from 'primeng/api';
import {TitleCasePipe} from '@angular/common';

@Component({
  selector: 'app-topic-details',
  imports: [
    Card,
    Button,
    PrimeTemplate,
    TitleCasePipe
  ],
  templateUrl: './topic-details.component.html',
  styleUrl: './topic-details.component.scss'
})
export class TopicDetailsComponent {

  @Input() topic!: Topic;
  @Input() isSubscriptionPage!: boolean;
  @Input() isSubscribed!: boolean;
  @Output() topicId: EventEmitter<number> = new EventEmitter<number>();

  emitTopicId(): void {
    this.topicId.emit(this.topic.id);
  }
}
