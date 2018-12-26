import { Injectable } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Subject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProgressBarService {

  private _displaySubject: Subject<boolean> = new Subject<boolean>();

  private _displayObservable$: Observable<boolean> = this._displaySubject.asObservable();
  public get displayObservable$(): Observable<boolean> {
    return this._displayObservable$;
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
