import {Component} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {AuthService} from '../../../features/auth/services/auth.service';
import {Drawer} from 'primeng/drawer';
import {Button} from 'primeng/button';

@Component({
  selector: 'app-header',
  imports: [
    Menubar,
    Image,
    RouterLink,
    Drawer,
    Button,
    PrimeTemplate,
    RouterOutlet
  ],
  templateUrl: './header-with-menu.component.html',
  styleUrl: './header-with-menu.component.scss'
})
export class HeaderWithMenuComponent {
  visible: boolean = false;

  items: MenuItem[] = [
    {
      label: 'Se déconnecter',
      styleClass: 'logout-item',
      command: (): void => this.logOut(),
    },
    {
      label: 'Articles',
      routerLink: '/user-posts-feed'
    },
    {
      label: 'Thèmes',
      routerLink: '/all-topics-list'
    },
    {
      icon: 'pi pi-user',
      routerLink: '/user-profile'
    }
  ];

  constructor(private readonly authService: AuthService,
              private readonly router: Router) {}


  logOut(): void {
    this.authService.logout();
  }

  navigateToItem(itemId: number): void {
    this.visible = false;
    const route: string = this.items.at(itemId)?.routerLink;

    this.router.navigateByUrl(route)
      .catch(console.error);
  }
}
