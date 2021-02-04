import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Store, select } from '@ngrx/store';
import { Subscription } from 'rxjs';
import { filter, switchMap } from 'rxjs/operators';
import { Item } from 'src/app/core/model/item.interface';
import { getProductById } from 'src/app/redux/product';

@Component({
  selector: 'app-personalizza-3',
  templateUrl: './personalizza-3.component.html',
  styleUrls: ['./personalizza-3.component.scss']
})
export class Personalizza3Component implements OnInit {

  private subscription: Subscription = new Subscription();

  imgPath:string;
  @Input()
  product: Item;


  constructor(private route: ActivatedRoute, private store: Store, private router: Router) { }



  ngOnInit(): void {
    this.subscription.add(this.route.params.pipe(
      filter(params => params != null && params['id'] != null),
      switchMap(params => this.store.pipe(select(getProductById, { id: Number(params['id']) }))),
    ).subscribe(product => {
      this.product = product;
      this.imgPath = this.product.immagine;
    }));
  }

}