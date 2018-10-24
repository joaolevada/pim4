import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeRoutingModule } from './home-routing.module';
import { AngularMaterialModule } from '../shared/angular-material/angular-material.module';
import { NavigatorComponent } from './navigator/navigator.component';

@NgModule({
  imports: [
    CommonModule,
    HomeRoutingModule,
    AngularMaterialModule,
  ],
  declarations: [DashboardComponent, NavigatorComponent]
})
export class HomeModule { }
