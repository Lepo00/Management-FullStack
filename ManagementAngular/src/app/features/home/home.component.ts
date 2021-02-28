import { Component, OnInit } from '@angular/core';
import { InvoiceMaster } from 'src/app/core/models/invoice-master.interface';
import { Item } from 'src/app/core/models/item.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  nCustomers:number;
  nItems:number;
  totInvoices:number;

  constructor(private httpService: HttpCommunicationsService) {
    this.totInvoices=0;
    let currentUser = <User>JSON.parse(sessionStorage.getItem("user"));
    let observer=this.httpService.retrieveGetCall<InvoiceMaster[]>("user/" + currentUser.id + "/invoices").subscribe(response => {
      response.map(invoice=>{
        this.totInvoices+=invoice.tail.finalAmount;
      })
      observer.unsubscribe();
    });
    let observer2=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.nItems=response.length;
      observer2.unsubscribe();
    });
    this.nCustomers=currentUser.customers.length;
  }

  ngOnInit(): void {
  }
  
}
