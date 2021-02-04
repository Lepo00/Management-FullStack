import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpCommunicationsService } from '../http-communication/http-communication.service';
import { User } from '../model/user.interface';

@Injectable()
export class loginRegisterService {

  constructor(private httpCommunications: HttpCommunicationsService) { }

  //LOGIN - HttpCommunicationService
  eseguiLogin(username: string, password: string): Observable<User[]>{
    return this.httpCommunications.retrieveGetCall<User[]>("users",{username, password});
  }

  //SIGNUP - HttpCommunicationService
  eseguiRegistrazione(user: User): Observable<User>{
    return this.httpCommunications.retrievePostCall<User>("users",user);
  }
}