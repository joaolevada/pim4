import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
// import { RequestOptions } from 'http';

@Injectable({
  providedIn: 'root',
  deps: [HttpClient]
})
export class HttpAuthClientService {

  // TODO: Recuperar os dados de autenticacao do localStorage

  constructor(
    private _internalHttp: HttpClient
  ) { }

  // private _buildRequestOptions(): RequestOptions {
  //   return null;
  // }

  get<T>(url: string): Observable<T> {
    return this._internalHttp.get<T>(url);
  }

  post<T>(url: string, body: any): Observable<T> {
    return this._internalHttp.post<T>(url, body);
  }

  put<T>(url: string, body: any): Observable<T> {
    return this._internalHttp.put<T>(url, body);
  }

  delete<T>(url: string): Observable<T> {
    return this._internalHttp.delete<T>(url);
  }

}
