import { Component } from '@angular/core';
import {PostDetailsComponent} from '../../features/posts/components/post-details/post-details.component';

@Component({
  selector: 'app-post-page',
  imports: [
    PostDetailsComponent
  ],
  templateUrl: './post-page.component.html',
  styleUrl: './post-page.component.scss'
})
export class PostPageComponent {

}
