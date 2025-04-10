import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';

@Component({
  selector: 'app-user-edit',
  imports: [
    InputText,
    ReactiveFormsModule,
    Button,
    Message
  ],
  templateUrl: './user-edit.component.html',
  styleUrl: './user-edit.component.scss'
})
export class UserEditComponent implements OnInit {

  @Input() userId!: number;
  userEditForm!: FormGroup;

  constructor(private readonly formBuilder: FormBuilder) {}


  ngOnInit(): void {
    this.userEditForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100), passwordComplexityValidator()]],
    });
  }

  onUpdate(): void {
    console.log(this.userEditForm.value);
  }
}
