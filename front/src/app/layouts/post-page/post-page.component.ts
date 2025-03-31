import { Component } from '@angular/core';
import {PostDetailsComponent} from '../../features/posts/components/post-details/post-details.component';
import {Button} from 'primeng/button';
import {Router} from '@angular/router';

@Component({
  selector: 'app-post-page',
  imports: [
    PostDetailsComponent,
    Button
  ],
  templateUrl: './post-page.component.html',
  styleUrl: './post-page.component.scss'
})
export class PostPageComponent {

  constructor(private readonly router: Router) {}

  goBack(): void {
    this.router.navigate(['/user-posts-feed'])
      .catch(console.error);
  }
}
