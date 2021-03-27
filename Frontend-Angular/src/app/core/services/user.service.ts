import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService:HttpCommunicationsService) { }

  update(id:number):Observable<User>{
    return this.httpService.retrieveGetCall<User>("user/"+id);
  }
}
