import {Action, createReducer, on} from '@ngrx/store';
import { Item } from 'src/app/core/model/Item.interface';
import { initProduct } from './product.action';

//definizione state
export interface ProductState {
  product: Item[];
}

//definizione stato iniziale dello state
export const initialState: ProductState = {
  product: []
};


export const productReducer = createReducer(
  initialState,
  on(initProduct, (state, {product}) => ({...state, product})),
   //State iniziale, veriabile da passare - aggiorno lo state del clothes
);


export function reducer(state: ProductState | undefined, action: Action) {
  return productReducer(state, action);
}
