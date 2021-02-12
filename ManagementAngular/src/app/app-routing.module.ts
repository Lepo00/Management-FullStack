import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { PagesGuard } from './core/guards/pages.guard';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'home', loadChildren: () => import('./features/home/home.module').then(m => m.HomeModule), canActivate: [AuthGuard],canLoad: [AuthGuard] },
  { path: 'invoices', loadChildren: () => import('./features/invoices/invoices.module').then(m => m.InvoicesModule), canActivate: [AuthGuard],canLoad: [AuthGuard]},
  { path: 'customers', loadChildren: () => import('./features/customers/customers.module').then(m => m.CustomersModule), canActivate: [AuthGuard],canLoad: [AuthGuard]},
  { path: 'login', loadChildren: () => import('./features/login/login.module').then(m => m.LoginModule), canActivate: [PagesGuard],canLoad: [PagesGuard] },
  { path: 'items', loadChildren: () => import('./features/item/item.module').then(m => m.ItemModule), canActivate: [AuthGuard],canLoad: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
