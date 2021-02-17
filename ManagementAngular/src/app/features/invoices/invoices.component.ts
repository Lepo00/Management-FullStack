import { Component, OnInit } from '@angular/core';
import { InvoiceBody } from 'src/app/core/models/invoice-body.interface';
import { InvoiceMaster } from 'src/app/core/models/invoice-master.interface';
import { InvoiceTail } from 'src/app/core/models/invoice-tail.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss']
})
export class InvoicesComponent implements OnInit {
  currentUser:User;
  constructor(private httpService: HttpCommunicationsService) { }
  invoices:InvoiceMaster[];
  arrayOne(n: number): any[] {
    return Array(n);
  }
  
  ngOnInit(): void {
    this.currentUser= <User>JSON.parse(sessionStorage.getItem("user"));
    this.httpService.retrieveGetCall<InvoiceMaster[]>("user/"+ this.currentUser.id +"/invoices").subscribe(response => {
      this.invoices = response;
    });
  }

}

