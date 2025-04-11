import {Component} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {RouterLink, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    Menubar,
    Image,
    PrimeTemplate,
    RouterLink,
    RouterOutlet
  ],
  templateUrl: './header-without-menu.component.html',
  styleUrl: './header-without-menu.component.scss'
})
export class HeaderWithoutMenuComponent {
}
