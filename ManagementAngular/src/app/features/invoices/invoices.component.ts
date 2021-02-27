import { DatePipe, formatDate } from '@angular/common';
import { Component, ElementRef, Inject, LOCALE_ID, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
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
  searchButton:boolean;
  currentUser: User;
  items: Item[];
  invoiceDetail: InvoiceMaster;
  invoices: InvoiceMaster[];
  invoiceForm: FormGroup;
  itemsArr: FormArray;

  constructor(private httpService: HttpCommunicationsService, private fb: FormBuilder, private route:ActivatedRoute, @Inject(LOCALE_ID) private locale: string) {
    this.invoiceForm = this.fb.group({
      accountholder: ['', Validators.required],
      date: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      rows: this.fb.array([this.createItem()]),
      tail: [0, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
    this.currentUser = <User>JSON.parse(sessionStorage.getItem("user"));
    this.invoices=this.currentUser.invoices.sort((a, b) => a.id - b.id);
    let observer=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
      observer.unsubscribe();
    });
    this.searchButton=true;
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if(params['search']!=null){
        this.filterCustomers(params['search']);
      }
    });
  }

  filterCustomers(search: any) {
    this.invoices=this.currentUser.invoices.sort((a, b) => a.id - b.id);
    this.searchButton=false;
    this.invoices= this.invoices.filter(invoice=>
      invoice.accountholder.includes(search) || invoice.date.includes(search) || invoice.paymentMethod.includes(search)
      )
  }

  createItem(): FormGroup {
    return this.fb.group({
      item: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.min(0)]],
      percDiscount: [0, [Validators.required, Validators.min(0), Validators.max(100)]]
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

  save() {
    const invoice = {} as InvoiceMaster;
    invoice.tail={} as InvoiceTail;
    invoice.rows=[] as InvoiceBody[];
    invoice.accountholder=this.invoiceForm.get('accountholder').value;
    invoice.date=formatDate(this.invoiceForm.get('date').value,'dd/MM/yyyy',this.locale);
    invoice.paymentMethod=this.invoiceForm.get('paymentMethod').value;
    invoice.tail.percDiscount=this.invoiceForm.get('tail').value;
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
  }

  calcs():InvoiceTail[]{
    const tail:InvoiceTail={percDiscount:0, discountValue:0, taxable:0};
    const rows:InvoiceTail={percDiscount:0, discountValue:0, taxable:0};

    //rows values
    if(this.items){
      this.invoiceForm.get('rows').value.map(row=>{
        if(row.item){
          rows.taxable+=this.items[row.item-1].price*row.quantity;
          rows.discountValue+=this.items[row.item-1].price*row.quantity*(row.percDiscount/100);
          rows.percDiscount+=row.percDiscount;
        }
      })
      rows.percDiscount/=this.invoiceForm.get('rows').value.length;
      rows.taxable-=rows.discountValue;
      rows.taxed=rows.taxable*22/100;
      rows.finalAmount=rows.taxed+rows.taxable;
    }

    //tail values
    if(this.invoiceForm.get('tail').value !=null)
      tail.percDiscount=this.invoiceForm.get('tail').value as number;
    tail.discountValue=rows.taxable*tail.percDiscount/100;
    tail.taxable=rows.taxable-tail.discountValue;
    tail.taxed=tail.taxable*22/100;
    tail.finalAmount=tail.taxed+tail.taxable;
      
    return [rows,tail];
  }
}
