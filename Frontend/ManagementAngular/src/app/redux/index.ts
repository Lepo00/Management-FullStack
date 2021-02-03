import { UsersState, usersReducer } from './users/users.reducers';
import { ActionReducerMap } from '@ngrx/store';
import { productReducer, ProductState } from './product/product.reducers';


//interfaccia rappresentante lo stato globale
export interface AppState {
    usersState: UsersState;
    productState: ProductState;

}

export const reducers: ActionReducerMap<AppState> = {
    usersState: usersReducer,
    productState: productReducer,

};