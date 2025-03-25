import {Component, OnInit} from '@angular/core';
import {Menubar} from 'primeng/menubar';
import {MenuItem} from 'primeng/api';
import {Image} from 'primeng/image';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    Menubar,
    Image,
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
        routerLink: ['/post-list']
      },
      {
        label: 'Thèmes',
        routerLink: ['/topic-list']
      },
      {
        icon: 'pi pi-user'
      }
    ]
  }
}
