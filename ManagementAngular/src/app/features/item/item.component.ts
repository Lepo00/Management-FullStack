import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Item } from 'src/app/core/models/item.interface';
import { ItemService } from 'src/app/core/services/item.service';

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
  constructor(private route: ActivatedRoute, private itemService:ItemService) {
    this.searchButton=true;
    this.subs.push(itemService.retrieveItems().subscribe(response=>{
      this.items=response;
      this.itemsTot=response;
    }));
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
