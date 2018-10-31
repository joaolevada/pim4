import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtendenteRoutingModule } from './atendente-routing.module';
import { CreateAtendenteComponent } from './view/create-atendente/create-atendente.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AngularMaterialModule } from '../shared/angular-material/angular-material.module';
import { ReadAtendenteComponent } from './view/read-atendente/read-atendente.component';
import { ReadAtendenteAllComponent } from './view/read-atendente-all/read-atendente-all.component';

@NgModule({
  imports: [
    CommonModule,
    AtendenteRoutingModule,
    AngularMaterialModule,
    ReactiveFormsModule,
  ],
  declarations: [CreateAtendenteComponent, ReadAtendenteComponent, ReadAtendenteAllComponent]
})
export class AtendenteModule { }
