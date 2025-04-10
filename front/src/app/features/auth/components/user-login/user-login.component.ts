import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {Router} from '@angular/router';
import {InputText} from 'primeng/inputtext';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Message} from 'primeng/message';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {LoginPayload} from '../../interfaces/requests/login-payload.interface';
import {AuthService} from '../../services/auth.service';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-user-login',
  imports: [
    InputText,
    ReactiveFormsModule,
    Button,
    Message
  ],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.scss'
})
export class UserLoginComponent implements OnInit {

  loginForm!: FormGroup;

  constructor(private readonly router: Router,
              private readonly formBuilder: FormBuilder,
              private readonly authService: AuthService) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usernameOrEmail: ['', [Validators.required, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100), passwordComplexityValidator()]]
    });
  };

  goBack(): void {
    this.navigateTo('/login-choice');
  };

  onLogin(): void {
    if (this.loginForm.valid) {
      const loginRequest: LoginPayload = this.loginForm.value;

      this.authService.getAuthToken(loginRequest).subscribe({
        next: () => this.navigateTo('/user-posts-feed'),
        error: (error) => this.handleLoginError(error)
      });
    }
  };


  private navigateTo(route: string): void {
    this.router.navigate([route])
      .catch(console.error);
  }

  private handleLoginError(error: HttpErrorResponse): void {
    if (error.status === 403) {
      console.error('Login failed', error);
      this.loginForm.setErrors({ loginFailed: true });
    }
  }
}
