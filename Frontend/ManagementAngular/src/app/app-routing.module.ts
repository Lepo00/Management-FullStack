import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginGuardService } from './core/guards/login-guard.service';


const routes: Routes = [
  { path: 'login', loadChildren: () => import('./features/login/login.module').then(m => m.LoginModule) },
  { path: 'signup', loadChildren: () => import('./features/signup/signup.module').then(m => m.SignupModule) },
  { path: 'home', loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule), canLoad: [LoginGuardService] },
  { path: 'personalizza', loadChildren: () => import('./features/personalizza/personalizza.module').then(m => m.PersonalizzaModule) },
  { path: 'cart', loadChildren: () => import('./features/cart/cart.module').then(m => m.CarrelloModule) },
  { path: 'personalizza-2', loadChildren: () => import('./features/personalizza-2/personalizza-2.module').then(m => m.Personalizza2Module) },
  { path: 'personalizza-3', loadChildren: () => import('./features/personalizza-3/personalizza-3.module').then(m => m.Personalizza3Module) },
  { path: 'personalizza-4', loadChildren: () => import('./features/personalizza-4/personalizza-4.module').then(m => m.Personalizza4Module) },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

