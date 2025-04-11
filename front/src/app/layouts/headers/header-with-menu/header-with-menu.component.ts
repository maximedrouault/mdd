import {Component} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from '../../../features/auth/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [
    Menubar,
    Image,
    PrimeTemplate,
    RouterLink,
    RouterOutlet
  ],
  templateUrl: './header-with-menu.component.html',
  styleUrl: './header-with-menu.component.scss'
})
export class HeaderWithMenuComponent {

  items: MenuItem[] = [
    {
      label: 'Se déconnecter',
      styleClass: 'logout-item',
      command: (): void => this.authService.logout()
    },
    {
      label: 'Articles',
      routerLink: ['/user-posts-feed']
    },
    {
      label: 'Thèmes',
      routerLink: ['/all-topics-list']
    },
    {
      icon: 'pi pi-user',
      routerLink: ['/user-profile']
    }
  ];

  constructor(private readonly authService: AuthService) {}
}
