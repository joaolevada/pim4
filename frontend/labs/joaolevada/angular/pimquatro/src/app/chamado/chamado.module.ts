import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReadChamadoAllComponent } from './view/read-chamado-all/read-chamado-all.component';
import { ChamadoService } from './service/chamado.service';
import { AngularMaterialModule } from '../shared/angular-material/angular-material.module';
import { CoreModule } from '../core/core.module';
import { ChamadoRoutingModule } from './chamado-routing.module';
import { ChamadoCardComponent } from './view/read-chamado-all/chamado-card/chamado-card.component';

@NgModule({
  imports: [
    CommonModule,
    CoreModule,
    AngularMaterialModule,
    ChamadoRoutingModule,
  ],
  declarations: [ReadChamadoAllComponent, ChamadoCardComponent],
  providers: [ChamadoService]
})
export class ChamadoModule { }
