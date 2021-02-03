import {createAction, props} from '@ngrx/store';
import {Item} from '../../core/model/Item.interface';

export const initProduct = createAction('[Clothe] init product',  props<{product: Item[]}>());

//effects
export const retrieveAllProduct = createAction('[Product] effects - Retrieve all');
export const updateProduct = createAction('[Product] Effect - Update Item' ) //Da implementare