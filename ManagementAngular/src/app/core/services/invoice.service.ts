import { Injectable } from '@angular/core';
import { InvoiceMaster } from '../models/invoice-master.interface';
import { User } from '../models/user';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  constructor(private httpService:HttpCommunicationsService) { }

  filterInvoices(search:string, invoices:InvoiceMaster[]):InvoiceMaster[]{
    invoices=invoices.sort((a, b) => a.id - b.id);
    invoices= invoices.filter(invoice=>
      invoice.accountholder.includes(search) || invoice.date.includes(search) || invoice.paymentMethod.includes(search)
    )
    return invoices;
  }

  save(id:number, invoice:InvoiceMaster):void{
    let observer = this.httpService.retrievePostCall<User>("user/"+id+"/addInvoice", invoice).subscribe(() => {
      observer.unsubscribe();
    })
  }

  update(id:number, invoice:InvoiceMaster):void{
    let observer = this.httpService.retrievePutCall<User>("invoice/update/"+id, invoice).subscribe(response => {
      observer.unsubscribe();
    })
  }

  delete(id:number):void{

  }
  
}
