import { DatePipe, formatDate } from '@angular/common';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
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
  currentUser: User;
  items: Item[];
  invoiceDetail: InvoiceMaster;
  invoices: InvoiceMaster[];
  invoiceForm: FormGroup;
  itemsArr: FormArray;

  constructor(private httpService: HttpCommunicationsService, private fb: FormBuilder, private datePipe: DatePipe) {
    this.invoiceForm = this.fb.group({
      accountholder: ['', Validators.required],
      date: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      rows: this.fb.array([this.createItem()]),
      tail: ['0', [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  createItem(): FormGroup {
    return this.fb.group({
      item: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(0)]],
      percDiscount: ['0', [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  addItem(): void {
    this.itemsArr = this.invoiceForm.get('rows') as FormArray;
    this.itemsArr.push(this.createItem());
  }

  deleteItem(id:number): void{
    this.itemsArr = this.invoiceForm.get('rows') as FormArray;
    this.itemsArr.removeAt(id);
  }

  ngOnInit(): void {
    this.currentUser = <User>JSON.parse(sessionStorage.getItem("user"));
    this.invoices = this.currentUser.invoices;
    let observer=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
      observer.unsubscribe();
    })
  }

  save() {
    const invoice = {} as InvoiceMaster;
    invoice.tail={} as InvoiceTail;
    invoice.rows=[] as InvoiceBody[];
    invoice.accountholder=this.invoiceForm.get('accountholder').value;
    invoice.date=this.invoiceForm.get('date').value;
    invoice.paymentMethod=this.invoiceForm.get('paymentMethod').value;
    invoice.tail.discountPerc=this.invoiceForm.get('tail').value;
    this.invoiceForm.get('rows').value.map((row,index)=>{
      invoice.rows[index]={} as InvoiceBody;
      invoice.rows[index].item={} as Item;
      invoice.rows[index].item.id=row.item;
      invoice.rows[index].quantity=row.quantity;
      invoice.rows[index].percDiscount=row.percDiscount;
    })
    let observer = this.httpService.retrievePostCall<User>("user/"+this.currentUser.id+"/addInvoice", invoice).subscribe(response => {
      this.updateUser();
      observer.unsubscribe();
    })
  }
  
  updateUser(){
    let observer=this.httpService.retrieveGetCall<User>("user/"+this.currentUser.id).subscribe(response=>{
      sessionStorage.setItem("user",JSON.stringify(response));
      this.invoices=response.invoices.sort((a, b) => a.id - b.id);
      observer.unsubscribe();
    })
  }

  delete(){
    let url:string="invoice/delete/"+this.invoiceDetail.id;
    let observer=this.httpService.retrieveDeleteCall<string>(url).subscribe(response=>{
      this.updateUser();
      observer.unsubscribe();
    });
  }

  detail(id: number) {
    this.invoiceDetail = this.invoices.find((i)=> i.id === id )
    console.log(this.invoiceDetail);
  }
}
