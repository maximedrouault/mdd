import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {InputText} from 'primeng/inputtext';
import {Message} from 'primeng/message';
import {RegisterPayload} from '../../interfaces/requests/register-payload.interface';
import {AuthService} from '../../services/auth.service';
import {LoginPayload} from '../../interfaces/requests/login-payload.interface';
import {catchError, Observable, of, switchMap} from 'rxjs';
import {AuthToken} from '../../interfaces/responses/auth-token';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-user-register',
  imports: [
    Button,
    ReactiveFormsModule,
    InputText,
    Message
  ],
  templateUrl: './user-register.component.html',
  styleUrl: './user-register.component.scss'
})
export class UserRegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private readonly router: Router,
              private readonly formBuilder: FormBuilder,
              private readonly authService: AuthService) {}


  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(100)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100), passwordComplexityValidator()]],
    })
  }

  goBack(): void {
    this.router.navigate(['auth-choice'])
      .catch(console.error);
  }

  onRegister(): void {
    if (this.registerForm.invalid) {
      return;
    }

    const registerRequest: RegisterPayload = this.registerForm.value;

    this.authService.registerUser(registerRequest).pipe(
      catchError((error) => this.handleRegistrationError(error)),
      switchMap(() => this.handleLoginIfNoError(registerRequest))
    ).subscribe({
      next: () => this.navigateToUserFeed(),
      error: (error) => this.handleLoginError(error)
    });
  }


  private handleRegistrationError(error: HttpErrorResponse): Observable<null> {
    if (error.status === 409) {
      console.error('Username or email already exists', error);
      this.registerForm.setErrors({ userAlreadyExists: true });
    } else {
      console.error('Registration failed', error);
      this.registerForm.setErrors({ registrationFailed: true });
    }
    // Return an observable with null to continue the stream
    return of(null);
  }

  private handleLoginIfNoError(registerRequest: RegisterPayload): Observable<AuthToken | null> {
     const loginRequest: LoginPayload = {
      usernameOrEmail: registerRequest.email,
      password: registerRequest.password
    };

    return this.authService.getAuthToken(loginRequest);
  }

  private navigateToUserFeed(): void {
    if (!this.registerForm.errors) {
      this.router.navigate(['/user-posts-feed'])
        .catch(console.error);
    }
  }

  private handleLoginError(error: HttpErrorResponse): void {
    console.error('Login failed', error);
    this.registerForm.setErrors({ loginFailed: true });
  }
}
