import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {Router} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {passwordComplexityValidator} from '../../validators/password-complexity.validator';
import {InputText} from 'primeng/inputtext';
import {Message} from 'primeng/message';

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
              private readonly  formBuilder: FormBuilder) {}


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
    console.log(this.registerForm.value);
  }
}
