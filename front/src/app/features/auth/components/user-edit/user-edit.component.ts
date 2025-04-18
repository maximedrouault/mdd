import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {InputText} from 'primeng/inputtext';
import {Button} from 'primeng/button';
import {Message} from 'primeng/message';
import {Observable, of} from 'rxjs';
import {UserInfos} from '../../interfaces/responses/user-infos.interface';
import {AuthService} from '../../services/auth.service';
import {UserEditPayload} from '../../interfaces/requests/user-edit-payload.interface';
import {HttpErrorResponse} from '@angular/common/http';

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

    this.userInfos$ = this.authService.getUserInfos();
    this.userInfos$.subscribe((userInfos: UserInfos) => {
      this.userEditForm.patchValue({
        username: userInfos.username,
        email: userInfos.email,
      });
    })
  }

  onUpdate(): void {
    if (this.userEditForm.valid) {
      const updatedUserInfos: UserEditPayload = this.userEditForm.value;

      this.authService.updateUserInfos(updatedUserInfos).subscribe({
        next: () => this.userEditForm.setErrors({ updateSuccess: true }),
        error: (error) => this.handleUpdateError(error)
      });
    }
  }

  private handleUpdateError(error: HttpErrorResponse): Observable<null> {
   if (error.status === 404) {
     console.error('User not found', error);
     this.userEditForm.setErrors({ userNotFound: true });
   } else {
     console.error('Update user failed', error);
      this.userEditForm.setErrors({ updateFailed: true });
   }
   // Return an observable with null to continue the stream
   return of(null);
  }
}
