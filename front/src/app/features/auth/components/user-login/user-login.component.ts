import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {Router} from '@angular/router';
import {InputText} from 'primeng/inputtext';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {Message} from 'primeng/message';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {LoginPayload} from '../../interfaces/requests/login-payload.interface';

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
              private readonly formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.maxLength(100)]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(100), passwordComplexityValidator()]]
    })
  }

  goBack(): void {
    this.router.navigate(['auth-choice'])
      .catch(console.error);
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      const loginRequest: LoginPayload = this.loginForm.value;

      console.log(loginRequest);
    }
  }
}
