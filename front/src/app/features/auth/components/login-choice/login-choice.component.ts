import { Component } from '@angular/core';
import {Image} from 'primeng/image';
import {Button} from 'primeng/button';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-login-choice',
  imports: [
    Image,
    Button,
    RouterLink
  ],
  templateUrl: './login-choice.component.html',
  styleUrl: './login-choice.component.scss'
})
export class LoginChoiceComponent {

}
