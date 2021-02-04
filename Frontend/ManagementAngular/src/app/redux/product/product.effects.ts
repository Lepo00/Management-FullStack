import { Injectable } from '@angular/core';
import { HttpCommunicationsService } from 'src/app/core/http-communication/http-communication.service';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {switchMap, map, catchError, tap} from 'rxjs/operators';
import { Item } from 'src/app/core/model/Item.interface';
import { initProduct, retrieveAllProduct } from './product.action';

@Injectable()
export class ProductEffects{

    constructor(private actions$: Actions, private httpCommunicationSercive: HttpCommunicationsService) {}

    retrieveAllProduct$ = createEffect(() => this.actions$.pipe(
        ofType(retrieveAllProduct),
        switchMap(() => this.httpCommunicationSercive.retrieveGetCall<Item[]>('product').pipe(
            tap(product => console.log(product)),
            map((product) => initProduct({product}))
        ))
    ));
}