import { Component } from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {HeaderComponent} from './layouts/shared/header/header.component';
import {registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'mdd-front';

  constructor(private readonly router: Router) {
    registerLocaleData(localeFr)
  }


  shouldShowHeader(): boolean {
    const excludedRoutes: string[] = ['/login-choice', '/user-login', 'user-register'];

    return !excludedRoutes.includes(this.router.url);
  }
}
