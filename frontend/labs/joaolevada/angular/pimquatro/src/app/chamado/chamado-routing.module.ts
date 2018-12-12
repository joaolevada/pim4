import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReadChamadoAllComponent } from './view/read-chamado-all/read-chamado-all.component';

const routes: Routes = [
  { path: '', component: ReadChamadoAllComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChamadoRoutingModule { }
