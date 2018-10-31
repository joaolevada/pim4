import { NgModule } from '@angular/core';
import {
  MatButtonModule,
  MatToolbarModule,
  MatSidenavModule,
  MatIconModule,
  MatListModule,
  MatGridListModule,
  MatMenuModule,
  MatCardModule,
  MatFormFieldModule,
  MatOptionModule,
  MatRadioModule,
  MatSelectModule,
  MatInputModule,
} from '@angular/material';
import { LayoutModule } from '@angular/cdk/layout';

@NgModule({
  imports: [
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    MatGridListModule,
    MatMenuModule,
    MatCardModule,
    MatFormFieldModule,
    MatOptionModule,
    MatRadioModule,
    MatSelectModule,
    MatInputModule,
  ],
  exports: [
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatButtonModule,
    MatGridListModule,
    MatMenuModule,
    MatCardModule,
    MatFormFieldModule,
    MatOptionModule,
    MatRadioModule,
    MatSelectModule,
    MatInputModule,
  ],
  declarations: []
})
export class AngularMaterialModule { }
