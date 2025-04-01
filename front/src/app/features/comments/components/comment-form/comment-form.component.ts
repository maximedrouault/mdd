import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {TextareaModule} from 'primeng/textarea';
import {Button} from 'primeng/button';
import {ActivatedRoute} from '@angular/router';
import {CommentPayload} from '../../interfaces/comment-payload.interface';

@Component({
  selector: 'app-comment-form',
  imports: [
    TextareaModule,
    FormsModule,
    Button,
    ReactiveFormsModule
  ],
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.scss'
})
export class CommentFormComponent implements OnInit {

  authorId: number = 3; // TODO: get the author id from the logged in user when the authentication is implemented
  postId!: number;
  commentForm!: FormGroup;
  commentPayload!: CommentPayload;

  constructor(private readonly activatedRoute: ActivatedRoute,
              private readonly formBuilder: FormBuilder) {}


  ngOnInit(): void {
    this.postId = this.activatedRoute.snapshot.params['id'];

    this.commentForm = this.formBuilder.group({
      comment: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.commentForm.valid) {
      const commentPayload: CommentPayload = {
        postId: this.postId,
        authorId: this.authorId,
        content: this.commentForm.value.comment
      }

      console.log('Form value: ', commentPayload);
    }
  }
}
