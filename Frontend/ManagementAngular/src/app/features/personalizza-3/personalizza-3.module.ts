import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Personalizza3RoutingModule } from './personalizza-3-routing.module';
import { Personalizza3Component } from './components/main/personalizza-3.component';


@NgModule({
  declarations: [Personalizza3Component],
  imports: [
    CommonModule,
    Personalizza3RoutingModule
  ]
})
export class Personalizza3Module { }