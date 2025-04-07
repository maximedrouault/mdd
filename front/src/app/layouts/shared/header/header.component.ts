import {Component, OnInit} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem, PrimeTemplate} from 'primeng/api';
import {Image} from 'primeng/image';
import {RouterLink} from '@angular/router';

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
  items: MenuItem[] | undefined;

  ngOnInit(): void {
    this.items = [
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
    ]
  }
}
