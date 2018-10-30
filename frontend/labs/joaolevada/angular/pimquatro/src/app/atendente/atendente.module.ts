import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtendenteRoutingModule } from './atendente-routing.module';
import { CreateAtendenteComponent } from './view/create-atendente/create-atendente.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../shared/angular-material/angular-material.module';

@NgModule({
  imports: [
    CommonModule,
    AtendenteRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule
  ],
  declarations: [CreateAtendenteComponent]
})
export class AtendenteModule { }
