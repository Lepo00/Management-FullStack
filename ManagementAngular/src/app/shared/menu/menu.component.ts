import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  @Input()
  search:boolean;

  constructor() { }

  ngOnInit(): void {
  }

  logout(): void{
    console.log("logout da implementare");
  }

}
