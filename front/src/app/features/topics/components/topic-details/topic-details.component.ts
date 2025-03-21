import {Component, Input} from '@angular/core';
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
  templateUrl: './topic-details.component.html',
  styleUrl: './topic-details.component.scss'
})
export class TopicDetailsComponent {

  @Input() topic: Topic | undefined;
  @Input() isSubscribed: boolean = false; // TODO: implement this feature

}
