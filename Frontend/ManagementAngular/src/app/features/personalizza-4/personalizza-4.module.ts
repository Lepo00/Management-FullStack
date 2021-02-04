import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Personalizza4RoutingModule } from './personalizza-4-routing.module';
import { Personalizza4Component } from './components/main/personalizza-4.component';


@NgModule({
  declarations: [Personalizza4Component],
  imports: [
    CommonModule,
    Personalizza4RoutingModule
  ]
})
export class Personalizza4Module { }