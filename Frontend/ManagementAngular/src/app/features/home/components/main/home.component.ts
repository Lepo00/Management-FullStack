import { Observable } from 'rxjs';
import { Store, select } from '@ngrx/store';
import { Component, OnInit } from '@angular/core';
import { map, filter } from 'rxjs/operators';
import { getCurrentUser } from 'src/app/redux/users';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  //return the first todo using NgRx store and pipe method
  
  id:number;

  constructor(private store: Store, private router: Router) {this.id=1}

  currentProductId(n:number){
    this.id = n;
    console.log(this.id);
  }

  goToPersonalizza(){
    this.router.navigate(['personalizza',this.id-1])
  }

  logout(){
    sessionStorage.setItem("user", '');
    this.router.navigateByUrl('/login');
  }

  ngOnInit(): void {}

}