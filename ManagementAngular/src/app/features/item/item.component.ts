import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Item } from 'src/app/core/models/item.interface';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';
import { ItemService } from 'src/app/core/services/item.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  //search:string;
  searchButton:boolean;
  items: Item[];
  itemsTot: Item[];
  constructor(private httpService: HttpCommunicationsService, private route: ActivatedRoute, private itemService:ItemService) { 
    this.searchButton=true;
    itemService.retrieveItems().subscribe(response=>{
      this.items=this.itemsTot=response;
    });
  }

  ngOnInit(): void {
    /*let observer=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
      this.itemsTot = response;*/
      this.route.params.subscribe(params => {
        if(params['search']!=null){
          this.filterItems(params['search']);
        }
      });
      /*observer.unsubscribe();
    });*/
  }
  
  filterItems(search:string) {
    this.items=this.itemsTot;
    this.searchButton=false;
    this.items=this.items.filter(item => item.description.includes(search) || item.code.includes(search));
  }

}
