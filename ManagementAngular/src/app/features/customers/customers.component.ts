import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from 'src/app/core/models/customer.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit, OnDestroy {
  idDelete: number;
  customerForm: FormGroup;
  currentUser:User;
  customers:Customer[];
  customer:Customer;

  constructor(private httpService: HttpCommunicationsService, private fb: FormBuilder) {
    this.customerForm = this.fb.group({
      name: ['', Validators.required],
      surname: '',
      ivaCode: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
    })
   }

  ngOnInit(): void {
    this.currentUser= <User>JSON.parse(sessionStorage.getItem("user"));
    this.customers=this.currentUser.customers;
  }

  add(){
    let url:string='user/'+this.currentUser.id+'/addCustomer';
    this.httpService.retrievePostCall<User>(url, this.customerForm.value).subscribe(response=>{
      this.setSession(response);
      }
    );
  }

  detail(id:number){
    this.customer=this.customers.find((c) => c.id === id);
  }

  popForm(){
    this.customerForm = this.fb.group({
      name: [this.customer.name, Validators.required],
      surname: this.customer?.surname,
      ivaCode: [this.customer.ivaCode, Validators.required],
      phone: [this.customer.phone, Validators.required],
      email: [this.customer.email, Validators.required],
      address: [this.customer.address, Validators.required],
    })
  }

  delete(){
    let url:string="user/"+this.currentUser.id+"/customer/"+this.idDelete;
    this.httpService.retrieveDeleteCall<User>(url).subscribe(response=>{
      this.setSession(response);
    });
  }

  edit(){
    let url:string="user/"+this.currentUser.id+"/customer/"+this.customer.id;
    this.httpService.retrievePutCall<User>(url, this.customerForm.value).subscribe(response=>{
      this.setSession(response);
    });
  }

  resetForm(){
    this.customerForm.reset();
  }

  setSession(response:User){
    sessionStorage.setItem("user",JSON.stringify(response));
    this.customers=response.customers;
  }

  ngOnDestroy(): void {
  }

}
