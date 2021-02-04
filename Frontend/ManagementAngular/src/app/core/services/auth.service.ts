import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpCommunicationsService } from '../http-communication/http-communication.service';
import { User } from '../model/user.interface';

@Injectable()
export class AuthService {

  constructor(private httpCommunications: HttpCommunicationsService) { }

  //LOGIN - HttpCommunicationService
  doLogin(username: string, password: string): Observable<User[]>{
    console.log("Do login auth service");
    return this.httpCommunications.retrieveGetCall<User[]>("users",{username, password});
  }

  //SIGNUP - HttpCommunicationService
  doSignUp(user: User): Observable<User>{
    console.log("Do signup auth service");
    return this.httpCommunications.retrievePostCall<User>("users",user);
  }
}
