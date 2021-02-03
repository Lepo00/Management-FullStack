import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Personalizza4Component } from './components/main/personalizza-4.component';


const routes: Routes = [{ path: '', component: Personalizza4Component }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Personalizza4RoutingModule { }