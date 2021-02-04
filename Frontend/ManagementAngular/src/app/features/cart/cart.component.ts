import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CarrelloComponent implements OnInit {

  prosegui:number;

  constructor() { }

  ngOnInit(): void {
    this.prosegui=0;
  }

  next(){
    this.prosegui+=1;
    console.log(this.prosegui)
  }
  back(){
    this.prosegui-=1;
    console.log(this.prosegui)
  }

}
