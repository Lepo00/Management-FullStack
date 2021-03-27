import { HttpParams } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  subs:Subscription[]=[];
  user:User;
  loginForm: FormGroup;

  constructor(fb: FormBuilder, private router: Router, private httpService: HttpCommunicationsService, private userService:UserService) {
    this.loginForm = fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void {
  }

  doLogin() {
    let body = new HttpParams();
    body = body.set('username', this.loginForm.get('username').value);
    body = body.set('password', this.loginForm.get('password').value);
    this.subs.push(this.httpService.retrievePostCall<User>("user/login", body).subscribe(response=>{
      if(response!=null){
        sessionStorage.setItem("user",JSON.stringify(response));
        this.router.navigateByUrl('home');
      }
    }))
  }

  ngOnDestroy(): void {
    this.subs.forEach((subscription) => subscription.unsubscribe());
  }

}
