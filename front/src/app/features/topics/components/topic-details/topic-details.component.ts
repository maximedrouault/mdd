import {Component, Input} from '@angular/core';
import {Topic} from '../../interfaces/topic.interface';
import {Card} from 'primeng/card';
import {PrimeTemplate} from 'primeng/api';
import {NgIf} from '@angular/common';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-topic-details',
  imports: [
    Card,
    PrimeTemplate,
    NgIf,
    Button
  ],
  templateUrl: './topic-details.component.html',
  styleUrl: './topic-details.component.scss'
})
export class TopicDetailsComponent {

  @Input() topic: Topic | undefined;
  // @Input() topic: Topic = { id: 1, name: 'Titre du thème', description: 'Description: lorem ipsum is simply dummy text of the' +
  //     ' printing and typesetting industry. Lorem Ipsum has been the industry\'s standard...' }; // TODO: remove this default value
  @Input() isSubscribed: boolean = false; // TODO: implement this feature
}
