import { DatePipe, formatDate } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { InvoiceBody } from 'src/app/core/models/invoice-body.interface';
import { InvoiceMaster } from 'src/app/core/models/invoice-master.interface';
import { InvoiceTail } from 'src/app/core/models/invoice-tail.interface';
import { Item } from 'src/app/core/models/item.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  providers: [DatePipe]
})
export class InvoicesComponent implements OnInit {
  currentUser:User;
  invoices:InvoiceMaster[];
  invoiceForm:FormGroup;
  itemForm:FormGroup;
  rows:InvoiceBody[]=[];
  invoiceToSave:InvoiceMaster;
  validatingForm: FormGroup;
  items: Item[];
  rowsTemp:InvoiceBody[]=[];
  myDate = new Date();

  constructor(private httpService: HttpCommunicationsService,private fb: FormBuilder,private datePipe: DatePipe) { 
  this.invoiceForm = this.fb.group({
    accountholder: ['', Validators.required],
    date: ['', Validators.required],
    paymentMethod: ['', Validators.required],
    rows: ['', Validators.required],
    tail: ['', Validators.required],
  })
  this.itemForm = this.fb.group({
    number:[],
    quantity:[""],
    percDiscount:[0],
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
    this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
    })
  }

  save(){
    this.invoiceToSave = {accountholder:this.currentUser.username,number:1,date:"12/12/12",paymentMethod:"bonifico",rows:this.rowsTemp,tail:{discountPerc:this.invoiceForm.value.tail,discountValue:0,finalAmount:0,itemsValue:0,rowsDiscount:0,taxable:0,taxed:0,totDiscount:0}}
    let observer = this.httpService.retrievePostCall<InvoiceMaster>("invoice/save",this.invoiceToSave).subscribe(response=>{
      this.assignInvoice();
      observer.unsubscribe();
    });
  }
  assignInvoice() {
    let observer = this.httpService.retrievePostCall<User>("user/"+this.currentUser.id+"/addInvoice",this.invoiceToSave).subscribe(response=>{
      this.currentUser = response;
      this.updateInvoice();
      observer.unsubscribe();
    })
  }
  updateInvoice(){
    let observer=this.httpService.retrieveGetCall<User>("user/"+this.currentUser.id).subscribe(response=>{
      sessionStorage.setItem("user",JSON.stringify(response));
      this.invoices=response.invoices.sort((a, b) => a.id - b.id);
      observer.unsubscribe();
    })
  }
 /*add(){
  this.rows = this.rowsTemp;
  console.log(this.rows)
}*/
addTemp(id:number){
  let newitem = this.items.find(it => it.id == id)
  let body:InvoiceBody = {finalAmount: 0,item:newitem,netPrice:0,percDiscount:this.itemForm.value.percDiscount,quantity:this.itemForm.value.quantity,taxable:0,taxed:0,totDiscount:0};
  this.rowsTemp.push(body)
  console.log(this.rowsTemp)
}

}

