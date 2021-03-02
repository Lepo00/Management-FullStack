import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService:HttpCommunicationsService) { }

  update(id:number):User{
    let user:User;
    let observer=this.httpService.retrieveGetCall<User>("user/"+id).subscribe(response=>{
      sessionStorage.setItem("user",JSON.stringify(response));
      user=response;
      observer.unsubscribe();
    })
    return user;

  }
}
