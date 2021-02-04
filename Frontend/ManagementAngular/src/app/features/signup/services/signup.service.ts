import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { Store } from '@ngrx/store';
import { User } from 'src/app/core/model/user.interface';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private router: Router, private authService: AuthService, private store: Store) { }

  executeSignUp(user: User){
    console.log("Signup service")

    this.authService.doSignUp(user).subscribe(()=>{"Signup ok"}, ()=>{"Signup error"})
  }
}
