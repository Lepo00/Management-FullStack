import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { switchMap, map } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { HttpCommunicationsService } from 'src/app/core/http-communication/http-communication.service';
import { retrieveAllUsers, initUsers, postUser, insertUser } from './users.action';
import { User } from 'src/app/core/model/user.interface';

@Injectable()
export class UsersEffect {

    retrieveAllUsers$ = createEffect(() => this.actions$.pipe(
        ofType(retrieveAllUsers),
        switchMap(() => this.httpCommunicationsService.retrieveGetCall<User[]>("users").pipe(
            map(users => initUsers({ users }))
        ))
    ));

    insertUser$ = createEffect(() => this.actions$.pipe(
        ofType(postUser),
        switchMap(action => this.httpCommunicationsService.retrievePostCall<User>("users/", action.user).pipe(
            switchMap(user => [insertUser({ user })])
        ))
    ))
    
    constructor(private actions$: Actions, private store: Store,
        private httpCommunicationsService: HttpCommunicationsService) {
    }

}