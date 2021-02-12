import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/core/models/user';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  user:User;
  loginForm: FormGroup;

  constructor(fb: FormBuilder, private router: Router, private httpService: HttpCommunicationsService) {
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
    if(this.loginForm.get('username').value==="admin", this.loginForm.get('password').value==="password"){
      sessionStorage.setItem('user', "test");
      this.router.navigateByUrl('home');
    }
    this.httpService.retrievePostCall<User>("user/login", body).subscribe(response=>{
      if(response!=null){
        sessionStorage.setItem("user",JSON.stringify(response));
        this.router.navigateByUrl('home');
      }
    })
  }

}
