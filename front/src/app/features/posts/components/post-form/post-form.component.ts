import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Select} from 'primeng/select';
import {Topic} from '../../../topics/interfaces/responses/topic.interface';
import {TopicsService} from '../../../topics/services/topics.service';
import {Message} from 'primeng/message';
import {InputText} from 'primeng/inputtext';
import {Textarea} from 'primeng/textarea';
import {PostPayload} from '../../interfaces/requests/post-payload-interface';
import {PostsService} from '../../services/posts.service';
import {AuthService} from '../../../auth/services/auth.service';

@Component({
  selector: 'app-post-form',
  imports: [
    Button,
    ReactiveFormsModule,
    Select,
    Message,
    InputText,
    Textarea
  ],
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.scss'
})
export class PostFormComponent implements OnInit {

  postForm!: FormGroup;
  topics!: Topic[];
  loggedUserId: number;

  constructor(private readonly router: Router,
              private readonly formBuilder: FormBuilder,
              private readonly topicsService: TopicsService,
              private readonly postService: PostsService,
              private readonly authService: AuthService) {
    this.loggedUserId = this.authService.getLoggedUserId();
  }

  ngOnInit(): void {
    this.topicsService.getAllTopics().subscribe(
      (topics => this.topics = topics)
    );

    this.postForm = this.formBuilder.group({
      selectedTopic: ['', [Validators.required]],
      title: ['', [Validators.required, Validators.maxLength(100)]],
      content: ['', [Validators.required, Validators.maxLength(65535)]]
    });
  }

  goBack(): void {
    this.navigateToUserPostsFeed()
  }

  onAddPost(): void {
    if (this.postForm.valid) {
      const postToAdd: PostPayload = {
        authorId: this.loggedUserId,
        topicId: this.postForm.value.selectedTopic,
        title: this.postForm.value.title,
        content: this.postForm.value.content
      }

      this.postService.savePost(postToAdd).subscribe(
        () => this.navigateToUserPostsFeed()
      );
    }
  }


  private navigateToUserPostsFeed(): void {
    this.router.navigate(['/user-posts-feed'])
      .catch(console.error);
  };
}
