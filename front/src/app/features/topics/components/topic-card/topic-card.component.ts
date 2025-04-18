import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Topic} from '../../interfaces/responses/topic.interface';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {PrimeTemplate} from 'primeng/api';
import {TitleCasePipe} from '@angular/common';

@Component({
  selector: 'app-topic-card',
  imports: [
    Card,
    Button,
    PrimeTemplate,
    TitleCasePipe
  ],
  templateUrl: './topic-card.component.html',
  styleUrl: './topic-card.component.scss'
})
export class TopicCardComponent {

  @Input() topic!: Topic;
  @Input() isSubscriptionPage!: boolean;
  @Input() isSubscribed!: boolean;
  @Output() topicId: EventEmitter<number> = new EventEmitter<number>();

  emitTopicId(): void {
    this.topicId.emit(this.topic.id);
  }
}
