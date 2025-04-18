import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {registerLocaleData} from '@angular/common';
import localeFr from '@angular/common/locales/fr';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title: string = 'mdd-front';

  constructor() {
    registerLocaleData(localeFr)
  }
}
