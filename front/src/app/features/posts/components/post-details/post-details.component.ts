import {Component, Input} from '@angular/core';
import {Card} from 'primeng/card';
import {Post} from '../../interfaces/post.interface';
import {PrimeTemplate} from 'primeng/api';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-post-details',
  imports: [
    Card,
    PrimeTemplate,
    DatePipe
  ],
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent {

  @Input() subscribedPost!: Post;
}
