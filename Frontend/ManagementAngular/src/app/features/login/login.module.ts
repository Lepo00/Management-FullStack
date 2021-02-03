import { NgModule } from '@angular/core';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './components/main/login.component';
import { SharedModule } from '../../shared/shared.module';
import { LoginService } from './services/login.service';


@NgModule({
  declarations: [LoginComponent],
  providers: [LoginService],
  imports: [
    SharedModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }




