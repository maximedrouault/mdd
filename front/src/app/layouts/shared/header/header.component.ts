import {Component, OnInit} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {NavigationCancel, NavigationEnd, NavigationSkipped, Router, RouterLink} from '@angular/router';
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
      .pipe(
        filter(event => event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationSkipped)
      )
      .subscribe(event => {
        let finalUrl: string = '';
        if (event instanceof NavigationEnd) {
          finalUrl = event.urlAfterRedirects;
        } else if (event instanceof NavigationCancel || event instanceof NavigationSkipped) {
          finalUrl = event.url;
        }
        console.log('URL finale :', finalUrl);
        this.showHeader = !this.headerExcludedRoutes.includes(finalUrl);
        this.showMenu = !this.menuExcludedRoutes.includes(finalUrl);
      });
  }
}
