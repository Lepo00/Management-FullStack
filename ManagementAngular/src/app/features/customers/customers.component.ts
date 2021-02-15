import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/core/models/customer.interface';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss']
})
export class CustomersComponent implements OnInit {
  arrayOne(n: number): any[] {
    return Array(n);
  }
  currentUser:User;
  customers:Customer[];
  constructor(private httpService: HttpCommunicationsService) { }

  ngOnInit(): void {
  this.currentUser= JSON.parse(sessionStorage.getItem("user"));
  this.customers=this.currentUser.customers;
  }

}
