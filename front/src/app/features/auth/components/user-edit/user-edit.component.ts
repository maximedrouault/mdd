import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';
import {Observable, of} from 'rxjs';
import {UserInfos} from '../../interfaces/responses/user-infos.interface';
import {AuthService} from '../../services/auth.service';

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
  userInfos$: Observable<UserInfos> = of();

  constructor(private readonly formBuilder: FormBuilder,
              private readonly authService: AuthService) {}


  ngOnInit(): void {
    this.userEditForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100), passwordComplexityValidator()]],
    });

    this.userInfos$ = this.authService.getUserInfo(this.userId);
    this.userInfos$.subscribe((userInfos: UserInfos) => {
      this.userEditForm.patchValue({
        username: userInfos.username,
        email: userInfos.email,
      });
    })
  }

  onUpdate(): void {
    console.log(this.userEditForm.value);
  }
}
