import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Item } from 'src/app/core/models/item.interface';
import { ItemService } from 'src/app/core/services/item.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  searchButton:boolean;
  items: Item[];
  itemsTot: Item[];
  constructor(private route: ActivatedRoute, private itemService:ItemService) {
    this.searchButton=true;
    itemService.retrieveItems().subscribe(response=>{
      this.items=response;
      this.itemsTot=response;
    });
  }

  ngOnInit(): void {
      let observer=this.route.params.subscribe(params => {
        if(params['search']!=null){
          this.filterItems(params['search']);
        }
        observer.unsubscribe();
      });
  }
  
  filterItems(search:string) {
    this.items=this.itemsTot;
    this.searchButton=false;
    this.items=this.items.filter(item => item.description.includes(search) || item.code.includes(search));
  }

}
