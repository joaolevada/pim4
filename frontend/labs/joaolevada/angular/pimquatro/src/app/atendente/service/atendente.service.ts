import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AtendenteResumoDto } from '../model/atendente-resumo-dto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AtendenteService {

  private _url = `${environment.url}/atendendes`;

  constructor(
    private _http: HttpClient
  ) {

  }

  buscar(): Promise<AtendenteResumoDto[]> {
    return this._http.get<AtendenteResumoDto[]>(this._url).toPromise();
    // return Promise.resolve(null);
  }

}
