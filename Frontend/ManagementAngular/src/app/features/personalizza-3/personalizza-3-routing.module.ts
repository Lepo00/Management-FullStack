import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Personalizza3Component } from './components/main/personalizza-3.component';


const routes: Routes = [{ path: '', component: Personalizza3Component }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Personalizza3RoutingModule { }