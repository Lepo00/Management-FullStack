import { createSelector, select } from '@ngrx/store';
import { AppState } from '..';
import { ProductState } from './product.reducers';


export const selectProductState = (state: AppState) => state.productState;


export const selectProduct = createSelector(
    selectProductState,
    (state: ProductState) => state.product
)

export const getProductById = createSelector(
    selectProductState,
    (state: ProductState, props: { id: number }) => state.product.find(item => item.id === props.id)
);
