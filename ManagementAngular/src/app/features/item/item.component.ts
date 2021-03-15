import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Item } from 'src/app/core/models/item.interface';
import { UnitOfMeasure } from 'src/app/core/models/unit-of-measure.interface';
import { ItemService } from 'src/app/core/services/item.service';
import { UnitOfMeasureService } from 'src/app/core/services/unit-of-measure.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit, OnDestroy {
  subs: Subscription[] = [];
  searchButton:boolean;
  items: Item[];
  itemsTot: Item[];
  measures: UnitOfMeasure[];
  itemForm:FormGroup;

  constructor(private route: ActivatedRoute,private fb:FormBuilder, private itemService:ItemService, private UnitOfMeasureService: UnitOfMeasureService) {
    this.searchButton=true;
    this.subs.push(
      itemService.retrieveItems().subscribe(response=>{
      this.items=response;
      this.itemsTot=response;
    }),
      UnitOfMeasureService.retrieveUnits().subscribe(response=>{
        this.measures=response;
      })
    );
    this.itemForm = this.fb.group({
      name: ['', Validators.required],
      surname: '',
      ivaCode: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.required],
      address: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.subs.push(this.route.params.subscribe(params => {
        if(params['search']!=null){
          this.filterItems(params['search']);
        }
      }))
  }
  
  filterItems(search:string) {
    this.items=this.itemsTot;
    this.searchButton=false;
    this.items=this.items.filter(item => item.description.includes(search) || item.code.includes(search));
  }

  ngOnDestroy(): void {
    this.subs.forEach((subscription) => subscription.unsubscribe())
  }

}
