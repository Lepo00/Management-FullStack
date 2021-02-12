import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @Input()
  search:boolean;

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  logout(): void{
    sessionStorage.removeItem('user');
    this.router.navigateByUrl('/login');
  }

}
