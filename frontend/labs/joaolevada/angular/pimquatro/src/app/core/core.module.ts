import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpAuthClientService } from './service/http-auth-client.service';
import { HttpClientModule } from '@angular/common/http';
import { ProgressBarService } from './service/progress-bar.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [
    HttpAuthClientService,
    ProgressBarService
  ],
  declarations: []
})
export class CoreModule { }
