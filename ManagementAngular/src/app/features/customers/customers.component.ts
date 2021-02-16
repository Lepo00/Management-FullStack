import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from 'src/app/core/models/customer.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  customerForm: FormGroup;
  currentUser:User;
  customers:Customer[];

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
    this.currentUser= JSON.parse(sessionStorage.getItem("user"));
    this.customers=this.currentUser.customers;
  }

  add(){
    let url:string='user/'+this.currentUser.id+'/addCustomer';
    this.httpService.retrievePostCall<string>(url, this.customerForm.value).subscribe();
  }

  updateUser(){
    this.httpService.retrieveGetCall<User>(this.currentUser.id+'').subscribe(response=>{
        this.currentUser=response
        this.customers=response.customers;
    })
  }

}
