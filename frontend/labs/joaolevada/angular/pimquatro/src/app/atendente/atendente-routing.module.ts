import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateAtendenteComponent } from './view/create-atendente/create-atendente.component';

const routes: Routes = [
  {path: 'create', component: CreateAtendenteComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtendenteRoutingModule { }
