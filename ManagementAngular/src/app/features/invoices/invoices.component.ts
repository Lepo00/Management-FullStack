import { formatDate } from '@angular/common';
import { Component, Inject, LOCALE_ID, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { InvoiceMaster } from 'src/app/core/models/invoice-master.interface';
import { InvoiceTail } from 'src/app/core/models/invoice-tail.interface';
import { Item } from 'src/app/core/models/item.interface';
import { User } from 'src/app/core/models/user';
import { InvoiceService } from 'src/app/core/services/invoice.service';
import { ItemService } from 'src/app/core/services/item.service';
import { UserService } from 'src/app/core/services/user.service';
import { DateCustomPipe } from 'src/app/shared/pipes/date-custom.pipe';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.scss'],
  providers: [DateCustomPipe]
})
export class InvoicesComponent implements OnInit, OnDestroy {
  subs: Subscription[] = [];
  searchButton:boolean;
  currentUser: User;
  items: Item[];
  invoiceDetail: InvoiceMaster;
  invoices: InvoiceMaster[];
  invoiceForm: FormGroup;
  itemsArr: FormArray;

  constructor(private fb: FormBuilder, private route:ActivatedRoute, @Inject(LOCALE_ID) private locale: string, private datepipe: DateCustomPipe,
   private invoiceService:InvoiceService, private itemService:ItemService, private userService:UserService) {
    this.invoiceForm = this.fb.group({
      accountholder: ['', Validators.required],
      date: ['', Validators.required],
      paymentMethod: ['', Validators.required],
      rows: this.fb.array([this.createItem()]),
      tail: [0, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
    this.currentUser = <User>JSON.parse(sessionStorage.getItem("user"));
    this.invoices=this.currentUser.invoices.sort((a, b) => a.id - b.id);
    this.subs.push(this.itemService.retrieveItems().subscribe(response=>{
      this.items=response;
    }));
    this.searchButton=true;
  }
  
  ngOnInit(): void {
    this.subs.push(this.route.params.subscribe(params => {
      if(params['search']!=null){
        this.invoices=this.invoiceService.filterInvoices(params['search'],this.currentUser.invoices);
        this.searchButton=false;
      }
    }));
  }

  createItem(item?, quantity?, percDiscount?): FormGroup {
    return this.fb.group({
      item: [item, Validators.required],
      quantity: [quantity, [Validators.required, Validators.min(0)]],
      percDiscount: [percDiscount, [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  addItem(item?, quantity?, percDiscount?): void {
    this.itemsArr = this.invoiceForm.get('rows') as FormArray;
    this.itemsArr.push(this.createItem(item, quantity, percDiscount));
  }

  addInvoice():void{
    this.invoiceForm.reset();
    this.itemsArr?.clear();
    this.itemsArr?.push(this.createItem());
  }

  populateForm(){
    this.invoiceForm = this.fb.group({
      accountholder: [this.invoiceDetail.accountholder, Validators.required],
      date: [this.datepipe.transform(this.invoiceDetail.date), Validators.required],
      paymentMethod: [this.invoiceDetail.paymentMethod, Validators.required],
      rows: this.fb.array([]),
      tail: [this.invoiceDetail.tail.percDiscount, [Validators.required, Validators.min(0), Validators.max(100)]]
    })
    this.invoiceDetail.rows.map(row=>{
      this.addItem(row.item.id, row.quantity, row.percDiscount);
    })
  }

  deleteItem(id:number): void{
    this.itemsArr = this.invoiceForm.get('rows') as FormArray;
    this.itemsArr.removeAt(id);
  }

  setInvoiceToSave():InvoiceMaster {
    const invoice:InvoiceMaster = {
      "accountholder":this.invoiceForm.get('accountholder').value,
      "date":formatDate(this.invoiceForm.get('date').value,'dd/MM/yyyy',this.locale),
      "paymentMethod":this.invoiceForm.get('paymentMethod').value,
      "tail":{
        "percDiscount":this.invoiceForm.get('tail').value,
      },
      "rows":[],
    };
    this.invoiceForm.get('rows').value.map((row,index)=>{
      invoice.rows[index]={
        "item":{ "id":row.item },
        "quantity":row.quantity,
        "percDiscount":row.percDiscount,
      }
    })
    return invoice;
  }

  saveInvoice(){
    this.subs.push(this.invoiceService.save(this.currentUser.id, this.setInvoiceToSave()).subscribe(()=>{
      this.updateUser();
    }));
    /*let invoice= this.setInvoiceToSave();
    let observer = this.httpService.retrievePostCall<User>("user/"+this.currentUser.id+"/addInvoice", invoice).subscribe(response => {
      observer.unsubscribe();
    })*/
  }

  updateInvoice(){
    this.subs.push(this.invoiceService.update(this.invoiceDetail.id, this.setInvoiceToSave()).subscribe(()=>{
      this.updateUser();
    }));
    /*let invoice= this.setInvoiceToSave();
    let observer = this.httpService.retrievePutCall<User>("invoice/update/"+this.invoiceDetail.id, invoice).subscribe(response => {
      this.updateUser();
      observer.unsubscribe();
    })*/
  }
  
  updateUser(){
    this.subs.push(this.userService.update(this.currentUser.id).subscribe(response=>{
      sessionStorage.setItem("user",JSON.stringify(response));
      this.invoices=response.invoices.sort((a, b) => a.id - b.id);
    }));
  }

  delete(){
    this.subs.push(this.invoiceService.delete(this.invoiceDetail.id).subscribe(()=>{
      this.updateUser();
    }));
    /*let url:string="invoice/delete/"+this.invoiceDetail.id;
    let observer=this.httpService.retrieveDeleteCall<string>(url).subscribe(response=>{
      this.updateUser();
      observer.unsubscribe();
    });*/
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
          rows.taxable+=this.items[row.item-1]?.price*row.quantity;
          rows.discountValue+=this.items[row.item-1]?.price*row.quantity*(row.percDiscount/100);
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

  ngOnDestroy(): void {
    this.subs.forEach((subscription) => subscription.unsubscribe());
  }
}