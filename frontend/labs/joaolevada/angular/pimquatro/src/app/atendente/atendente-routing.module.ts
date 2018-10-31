import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateAtendenteComponent } from './view/create-atendente/create-atendente.component';
import { ReadAtendenteComponent } from './view/read-atendente/read-atendente.component';
import { ReadAtendenteAllComponent } from './view/read-atendente-all/read-atendente-all.component';

const routes: Routes = [
  {path: 'create', component: CreateAtendenteComponent},
  {path: 'read', component: ReadAtendenteAllComponent},
  {path: 'read/:id', component: ReadAtendenteComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AtendenteRoutingModule { }
