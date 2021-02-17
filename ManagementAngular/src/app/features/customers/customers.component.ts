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
  customerForm: FormGroup;
  currentUser:User;
  customers:Customer[];
  customer:Customer;

  constructor(private httpService: HttpCommunicationsService, fb: FormBuilder) {
    this.customerForm = fb.group({
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
      sessionStorage.setItem("user",JSON.stringify(response));
      this.customers=response.customers;
      }
    );
  }

  detail(id:Number){
    console.log('detail: '+id);
    console.log(this.customers.find((c) => c.id === id));
    this.customer=this.customers.find((c) => c.id === id);
  }

  delete(){
    console.log('delete');
  }

  resetForm(){
    this.customerForm.reset();
  }

  ngOnDestroy(): void {
  }

}
