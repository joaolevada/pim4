import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  // TODO: Recuperar estes valores do localstorage
  get login(): string {
    return 'atendente@pimquatro.com';
  }
  get senha(): string {
    return '123456';
  }

  private _httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(this.login + ':' + this.senha),
    })
  };

  get<T>(url: string): Observable<T> {
    return this._internalHttp.get<T>(url, this._httpOptions);
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
