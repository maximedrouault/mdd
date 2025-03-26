import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Topic} from '../../interfaces/topic.interface';
import {Card} from 'primeng/card';
import {Button} from 'primeng/button';
import {PrimeTemplate} from 'primeng/api';

@Component({
  selector: 'app-topic-details',
  imports: [
    Card,
    Button,
    PrimeTemplate
  ],
  templateUrl: './topic-subscribe.component.html',
  styleUrl: './topic-subscribe.component.scss'
})
export class TopicSubscribeComponent {

  @Input() topic!: Topic;
  @Input() isSubscribed!: boolean;
  @Output() topicId: EventEmitter<number> = new EventEmitter<number>();

  onSubscribe(): void {
    this.topicId.emit(this.topic.id);
  }
}
