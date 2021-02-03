import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PersonalizzaRoutingModule } from './personalizza-routing.module';
import { PersonalizzaComponent } from './components/main/personalizza.component';


@NgModule({
  declarations: [PersonalizzaComponent],
  imports: [
    CommonModule,
    PersonalizzaRoutingModule
  ]
})
export class PersonalizzaModule { }