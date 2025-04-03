import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {TextareaModule} from 'primeng/textarea';
import {Button} from 'primeng/button';
import {CommentPayload} from '../../interfaces/requests/comment-payload.interface';
import {CommentsService} from '../../services/comments.service';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-comment-form',
  imports: [
    TextareaModule,
    FormsModule,
    Button,
    ReactiveFormsModule,
    Message
  ],
  templateUrl: './comment-form.component.html',
  styleUrl: './comment-form.component.scss'
})
export class CommentFormComponent implements OnInit {

  @Input() postId!: number;
  @Input() authorId!: number;
  commentForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder,
              private readonly commentsService: CommentsService) {}


  ngOnInit(): void {
    this.commentForm = this.formBuilder.group({
      comment: ['', [Validators.required, Validators.maxLength(255)]]
    });
  }

  onSubmit(): void {
    if (this.commentForm.valid) {
      const commentToAdd: CommentPayload = {
        postId: this.postId,
        authorId: this.authorId,
        content: this.commentForm.value.comment
      }

      this.commentsService.saveComment(commentToAdd).subscribe();
      this.commentForm.reset();
    }
  }
}
