import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CarrelloComponent } from './cart.component';

const routes: Routes = [{ path: '', component: CarrelloComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CarrelloRoutingModule { }
