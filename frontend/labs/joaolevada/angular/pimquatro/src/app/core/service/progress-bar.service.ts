import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProgressBarService {

  private _displaySubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(true);

  public get display$(): Observable<boolean> {
    return this._displaySubject.asObservable();
  }

  constructor() {
  }

  display() {
    this._displaySubject.next(true);
  }

  hide() {
    this._displaySubject.next(false);
  }

}
