import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer.interface';
import { User } from '../models/user';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpService:HttpCommunicationsService) { }

  save(id:number, customer:Customer):Observable<User>{
    let url:string='user/'+id+'/addCustomer';
    return this.httpService.retrievePostCall<User>(url, customer);
  }

  delete(id:number):Observable<string>{
    let url:string="customer/delete/"+id;
    return this.httpService.retrieveDeleteCall<string>(url);
  }

  update(id:number, customer:Customer):Observable<Customer>{
    let url:string="customer/update/"+id;
    return this.httpService.retrievePutCall<Customer>(url, customer);
  }

}
