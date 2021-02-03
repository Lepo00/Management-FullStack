import { UsersState } from './users.reducers';
import { createSelector } from '@ngrx/store';
import { AppState } from '..';

export const selectUsersState = (state: AppState) => state.usersState;

export const getCurrentUser = createSelector(
    selectUsersState,
    (state: UsersState) => state.currentUser
);