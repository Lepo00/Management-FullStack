import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  invoices:InvoiceMaster[];
  invoiceForm:FormGroup;
  itemForm:FormGroup;
  invoiceToSave:InvoiceMaster;
  validatingForm: FormGroup;
  constructor(private httpService: HttpCommunicationsService,private fb: FormBuilder) { 
  this.invoiceForm = this.fb.group({
    accountholder: ['', Validators.required],
    date: ['', Validators.required],
    paymentMethod: ['', Validators.required],
    rows: ['', Validators.required],
    tail: ['', Validators.required],
  })
  this.itemForm = this.fb.group({
    quantity:[""],
    percDiscount:[""],
    name:[""],
  })
}
  arrayOne(n: number): any[] {
    return Array(n);
  }
  
  ngOnInit(): void {
    this.currentUser= <User>JSON.parse(sessionStorage.getItem("user"));
    this.httpService.retrieveGetCall<InvoiceMaster[]>("user/"+ this.currentUser.id +"/invoices").subscribe(response => {
      this.invoices = response;
    });
  }

  save(){
    this.invoiceToSave.tail.discountPerc = this.invoiceForm.value.tail;
    this.httpService.retrievePostCall<InvoiceMaster>("invoice/save",this.invoiceToSave)
}
 add(){

}
}
