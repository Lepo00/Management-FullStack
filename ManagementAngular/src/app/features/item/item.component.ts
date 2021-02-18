import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Item } from 'src/app/core/models/item.interface';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  //search:string;
  searchButton:boolean;
  items: Item[];
  constructor(private httpService: HttpCommunicationsService, private route: ActivatedRoute) { 
    this.searchButton=true;
  }

  ngOnInit(): void {
    let observer=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
      this.route.params.subscribe(params => {
        let search= params['search'];
        if(search!=null){
          this.searchButton=false;
          this.filterItems(search);
        }
      });
      observer.unsubscribe();
    });
  }
  
  filterItems(search:string) {
    this.items=this.items.filter(item => item.description.includes(search) || item.code.includes(search));
  }

  /*get items(): Observable<Item[]>{
    return this.httpService.retrieveGetCall<Item[]>("item");
  }*/

}
