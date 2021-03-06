import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReadChamadoAllComponent } from './view/read-chamado-all/read-chamado-all.component';
import { ReadChamadoComponent } from './view/read-chamado/read-chamado.component';

const routes: Routes = [
  { path: '', component: ReadChamadoAllComponent },
  { path: ':protocolo', component: ReadChamadoComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChamadoRoutingModule { }
