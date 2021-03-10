import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { HttpCommunicationsService } from './services/http-communications.service';
import { InvoiceService } from './services/invoice.service';
import { UserService } from './services/user.service';

@NgModule({
  declarations: [],
  imports: [
    SharedModule,
    HttpClientModule
  ],
  providers: [HttpCommunicationsService, UserService, InvoiceService],
})
export class CoreModule { }
