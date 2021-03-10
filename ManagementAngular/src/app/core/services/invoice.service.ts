import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { InvoiceMaster } from '../models/invoice-master.interface';
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

  save(id:number, invoice:InvoiceMaster):Observable<InvoiceMaster>{
    return this.httpService.retrievePostCall<InvoiceMaster>("user/"+id+"/addInvoice", invoice);
  }

  update(id:number, invoice:InvoiceMaster):Observable<InvoiceMaster>{
    return this.httpService.retrievePutCall<InvoiceMaster>("invoice/update/"+id, invoice)
  }

  delete(id:number):Observable<string>{
    let url:string="invoice/delete/"+id;
    return this.httpService.retrieveDeleteCall<string>(url);
  }

  xlsx(id:number){
    return this.httpService.downloadXlsx("invoice/"+id+"/csv");
  }

}
