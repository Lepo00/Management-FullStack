import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UnitOfMeasure } from '../models/unit-of-measure.interface';
import { HttpCommunicationsService } from './http-communications.service';

@Injectable({
  providedIn: 'root'
})
export class UnitOfMeasureService {

  constructor(private httpService:HttpCommunicationsService) { }

  retrieveUnits():Observable<UnitOfMeasure[]>{
    return this.httpService.retrieveGetCall<UnitOfMeasure[]>("unit");
  }
}
