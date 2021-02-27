import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/core/models/customer.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  searchButton:boolean;
  idDelete: number;
  customerForm: FormGroup;
  currentUser:User;
  customers:Customer[];
  customer:Customer;

  constructor(private httpService: HttpCommunicationsService, private fb: FormBuilder, private route:ActivatedRoute) {
    this.searchButton=true;
    this.customerForm = this.fb.group({
      name: ['', Validators.required],
      surname: '',
      ivaCode: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
    });
    this.currentUser= <User>JSON.parse(sessionStorage.getItem("user"));
    this.customers=this.currentUser.customers.sort((a, b) => a.id - b.id);
   }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if(params['search']!=null){
        this.filterCustomers(params['search']);
      }
    });
  }

  filterCustomers(search: any) {
    this.customers=this.currentUser.customers.sort((a, b) => a.id - b.id);
    this.searchButton=false;
    this.customers= this.customers.filter(customer=>
       customer.email.includes(search) || customer.ivaCode.includes(search) || customer.name.includes(search)
      )
  }

  add(){
    let url:string='user/'+this.currentUser.id+'/addCustomer';
    let observer=this.httpService.retrievePostCall<User>(url, this.customerForm.value).subscribe(response=>{
      this.updateUser();
      observer.unsubscribe();
      })
  }

  delete(){
    let url:string="customer/delete/"+this.customer.id;
    let observer=this.httpService.retrieveDeleteCall<string>(url).subscribe(response=>{
      this.updateUser();
      observer.unsubscribe();
    });
  }

  edit(){
    let url:string="customer/update/"+this.customer.id;
    let observer=this.httpService.retrievePutCall<User>(url, this.customerForm.value).subscribe(()=>{
      this.updateUser();
      observer.unsubscribe();
    });
  }

  detail(id:number){
    this.customer=this.customers.find((c) => c.id === id);
  }

  updateUser(){
    let observer=this.httpService.retrieveGetCall<User>("user/"+this.currentUser.id).subscribe(response=>{
      sessionStorage.setItem("user",JSON.stringify(response));
      this.customers=response.customers.sort((a, b) => a.id - b.id);
      observer.unsubscribe();
    })
  }

  populateForm(){
    this.customerForm = this.fb.group({
      name: [this.customer.name, Validators.required],
      surname: this.customer?.surname,
      ivaCode: [this.customer.ivaCode, Validators.required],
      phone: [this.customer.phone, Validators.required],
      email: [this.customer.email, Validators.required],
      address: [this.customer.address, Validators.required],
    })
  }

  resetForm(){
    this.customerForm.reset();
  }
}
