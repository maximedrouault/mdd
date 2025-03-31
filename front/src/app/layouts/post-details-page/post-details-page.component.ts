import { Component } from '@angular/core';
import {PostDetailsComponent} from '../../features/posts/components/post-details/post-details.component';
import {Button} from 'primeng/button';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post-details-page',
  imports: [
    PostDetailsComponent,
    Button
  ],
  templateUrl: './post-details-page.component.html',
  styleUrl: './post-details-page.component.scss'
})
export class PostDetailsPageComponent {

  constructor(private readonly router: Router) {}

  goBack(): void {
    this.router.navigate(['/user-posts-feed'])
      .catch(console.error);
  }
}
