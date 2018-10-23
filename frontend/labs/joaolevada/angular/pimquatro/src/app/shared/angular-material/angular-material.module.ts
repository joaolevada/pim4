import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatToolbarModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatGridListModule,
  MatMenuModule,
  MatCardModule
} from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';

@NgModule({
  imports: [
    LayoutModule,
    MatButtonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatGridListModule,
    MatMenuModule,
    MatCardModule,
  ],
  exports: [
    LayoutModule,
    MatButtonModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatGridListModule,
    MatMenuModule,
    MatCardModule
  ],
  declarations: []
})
export class AngularMaterialModule { }
