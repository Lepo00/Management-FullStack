import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import {FormsModule} from '@angular/forms';
import {ReactiveFormsModule} from '@angular/forms';
import { RouterModule } from '@angular/router';
import { EurPipe } from './pipes/eur.pipe';
import { PercPipe } from './pipes/perc.pipe';



@NgModule({
  declarations: [MenuComponent, FooterComponent, EurPipe, PercPipe],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  exports:[
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    MenuComponent,
    FooterComponent,
    EurPipe,
    PercPipe
  ]
})
export class SharedModule { }
