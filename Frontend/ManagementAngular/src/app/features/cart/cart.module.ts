import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarrelloRoutingModule } from './cart-routing.module';
import { CarrelloComponent } from './cart.component';


@NgModule({
  declarations: [CarrelloComponent],
  imports: [
    CommonModule,
    CarrelloRoutingModule
  ]
})
export class CarrelloModule { }
