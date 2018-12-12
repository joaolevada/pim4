import { Injectable } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProgressBarService {

  private _displaySubject: Subject<boolean> = new Subject<boolean>();
  public get displaySubject(): Subject<boolean> {
    return this._displaySubject;
  }

  constructor() {

  }

  display() {
    this.displaySubject.next(true);
  }

  hide() {
    this.displaySubject.next(false);
  }

}
