import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AtendenteResumoDto } from '../model/atendente-resumo-dto';
import { HttpAuthClientService } from 'src/app/core/service/http-auth-client.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AtendenteService {

  private _url = `${environment.url}/atendentes`;

  constructor(
    private _http: HttpAuthClientService
  ) {

  }

  async buscar(): Promise<AtendenteResumoDto[]> {
    const atendentes = await this._http.get<AtendenteResumoDto[]>(this._url)
      .toPromise()
      .then(response => response);
    return atendentes;
  }

}
