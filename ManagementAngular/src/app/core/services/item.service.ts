import { Injectable } from '@angular/core';
import { Item } from '../models/item.interface';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private httpService:HttpCommunicationsService) { }

  retrieveItems():Item[]{
    let items:Item[];
    let observer=this.httpService.retrieveGetCall<Item[]>("item").subscribe(response => {
      items = response;
      observer.unsubscribe();
    });
    return items;
  }
}
