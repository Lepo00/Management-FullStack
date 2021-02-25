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
  invoiceDetail: InvoiceMaster;
  currentUser: User;
  invoices: InvoiceMaster[];
  invoiceForm: FormGroup;
  rows: InvoiceBody[] = [];
  invoiceToSave: InvoiceMaster;
  validatingForm: FormGroup;
  items: Item[];
  rowsTemp: InvoiceBody[] = [];
  itemsArr: FormArray;

  constructor(private httpService: HttpCommunicationsService, private fb: FormBuilder, private datePipe: DatePipe) {
    this.invoiceForm = this.fb.group({
      accountholder: ['ciao', Validators.required],
      date: ['14/08/2000', Validators.required],
      paymentMethod: ['paypal', Validators.required],
      rows: this.fb.array([this.createItem()]),
      tail: ['12', Validators.required]
    })
  }

  createItem(): FormGroup {
    return this.fb.group({
      item: ['', Validators.required],
      quantity: ['12', Validators.required],
      percDiscount: ['12', Validators.required]
    });
  }

  addItem(): void {
    this.itemsArr = this.invoiceForm.get('rows') as FormArray;
    this.itemsArr.push(this.createItem());
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
      this.updateUser(response);
      observer.unsubscribe();
    })
  }

  updateUser(response: User){
      sessionStorage.setItem("user",JSON.stringify(response));
      this.invoices=response.invoices.sort((a, b) => a.id - b.id);
  }

  detail(id: number) {
    console.log("id: "+id);
    this.invoiceDetail = this.invoices.find((i)=> i.id === id )
  }
}
