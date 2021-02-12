import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../shared/shared.module';
import { CoreModule } from '../core/core.module';



@NgModule({
  declarations: [],
  imports: [
    SharedModule,
    CoreModule
  ]
})
export class FeaturesModule { }
