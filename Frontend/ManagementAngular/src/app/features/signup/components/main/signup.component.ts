import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SignupService } from '../../services/signup.service';
import { User } from 'src/app/core/model/user.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  
  signUpForm: FormGroup;
  user: User = {
    username: null,
    surname: null,
    name: null,
    password: null,
  }

  constructor(fb: FormBuilder, private signUpService: SignupService, private router:Router) {
    this.signUpForm = fb.group({
      username: ['', Validators.required],
      name: [''],
      surname: [''],
      password: ['', Validators.required]
    })
  }

  ngOnInit(): void {}

  doSignUp() {
    this.user.username=this.signUpForm.get('username').value;
    this.user.name=this.signUpForm.get('name').value;
    this.user.surname=this.signUpForm.get('surname').value;
    this.user.password=this.signUpForm.get('password').value;

    console.log("DoSignUp method - signupComponent.ts");
    console.log(this.user);

    this.signUpService.executeSignUp(this.user);
    this.router.navigateByUrl('login');
  }


}
