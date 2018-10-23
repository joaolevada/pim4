import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeRoutingModule } from './home-routing.module';
import { AngularMaterialModule } from '../shared/angular-material/angular-material.module';
import { NavigatorComponent } from './navigator/navigator.component';
import { LayoutModule } from '@angular/cdk/layout';

@NgModule({
  imports: [
    CommonModule,
    LayoutModule,
    HomeRoutingModule,
    AngularMaterialModule
  ],
  declarations: [DashboardComponent, NavigatorComponent]
})
export class HomeModule { }
