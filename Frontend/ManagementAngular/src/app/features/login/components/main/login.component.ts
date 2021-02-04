import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(fb: FormBuilder, private loginService: LoginService) {
    this.loginForm = fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void {
  }

  doLogin() {
    this.loginService.executeLogin(this.loginForm.get('username').value, this.loginForm.get('password').value);
    //LOG
    console.log(this.loginForm.get('username').value)
    console.log(this.loginForm.get('password').value)
  }

}
