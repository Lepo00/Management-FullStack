import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Personalizza2Component } from './components/main/personalizza-2.component';


const routes: Routes = [{ path: '', component: Personalizza2Component }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Personalizza2RoutingModule { }