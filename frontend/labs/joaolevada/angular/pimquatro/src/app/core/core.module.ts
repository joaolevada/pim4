import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpAuthClientService } from './service/http-auth-client.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ],
  providers: [HttpAuthClientService],
  declarations: []
})
export class CoreModule { }
