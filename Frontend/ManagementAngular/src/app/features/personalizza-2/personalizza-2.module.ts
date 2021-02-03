import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Personalizza2RoutingModule } from './personalizza-2-routing.module';
import { Personalizza2Component } from './components/main/personalizza-2.component';


@NgModule({
  declarations: [Personalizza2Component],
  imports: [
    CommonModule,
    Personalizza2RoutingModule
  ]
})
export class Personalizza2Module { }