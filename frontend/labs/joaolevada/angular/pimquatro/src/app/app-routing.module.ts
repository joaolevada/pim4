import { NgModule } from '@angular/core';
import { Routes, RouterModule, ChildrenOutletContexts } from '@angular/router';

const routes: Routes = [
  /*
  * Módulo raiz da interface de usuário
  */
  // { path: 'home', loadChildren: './home/home.module#HomeModule' },

  /**
   * Módulo Atendente CRUD
   */
  { path: 'atendente', loadChildren: './atendente/atendente.module#AtendenteModule' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [ChildrenOutletContexts]
})
export class AppRoutingModule { }
