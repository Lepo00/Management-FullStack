import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../models/item.interface';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private httpService:HttpCommunicationsService) { }

  retrieveItems():Observable<Item[]>{
    return this.httpService.retrieveGetCall<Item[]>("item");
  }
}
