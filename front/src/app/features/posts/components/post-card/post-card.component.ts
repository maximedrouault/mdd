import {Component, Input} from '@angular/core';
import {Card} from 'primeng/card';
import {Post} from '../../interfaces/responses/post.interface';
import {PrimeTemplate} from 'primeng/api';
import {DatePipe, TitleCasePipe} from '@angular/common';

@Component({
  selector: 'app-post-card',
  imports: [
    Card,
    PrimeTemplate,
    DatePipe,
    TitleCasePipe
  ],
  templateUrl: './post-card.component.html',
  styleUrl: './post-card.component.scss'
})
export class PostCardComponent {

  @Input() post!: Post;
}
