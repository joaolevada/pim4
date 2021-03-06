import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpAuthClientService } from 'src/app/core/service/http-auth-client.service';
import { ChamadoResumoDto } from '../model/chamado-resumo-dto';

@Injectable({
  providedIn: 'root'
})
export class ChamadoService {

  private _url = `${environment.url}/chamados`;

  constructor(
    private _http: HttpAuthClientService
  ) { }

  buscar(): Promise<ChamadoResumoDto[]> {

    const url = this._url;
    return this._http
      .get<ChamadoResumoDto[]>(url)
      .toPromise();

  }


}
