import {Component, OnInit} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {NavigationEnd, Router, RouterLink} from '@angular/router';
import {filter} from 'rxjs';

@Component({
  selector: 'app-header',
  imports: [
    Menubar,
    Image,
    PrimeTemplate,
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {
  private readonly headerExcludedRoutes: string[] = ['/login-choice'];
  private readonly menuExcludedRoutes: string[] = ['/user-login', '/user-register'];
  showHeader: boolean = false;
  showMenu: boolean = false;

  items: MenuItem[] = [
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

  constructor(private readonly router: Router) {}


  ngOnInit(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        this.showHeader = !this.headerExcludedRoutes.includes(this.router.url);
        this.showMenu = !this.menuExcludedRoutes.includes(this.router.url);
      });
  }
}
