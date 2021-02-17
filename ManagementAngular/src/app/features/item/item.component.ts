import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from 'src/app/core/models/item.interface';
import { HttpCommunicationsService } from 'src/app/core/services/http-communications.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.scss']
})
export class ItemComponent implements OnInit {
  items: Item[];
  constructor(private httpService: HttpCommunicationsService) { }

  ngOnInit(): void {
    this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      this.items = response;
    });
  }

  /*get items(): Observable<Item[]>{
    return this.httpService.retrieveGetCall<Item[]>("item");
  }*/

}
