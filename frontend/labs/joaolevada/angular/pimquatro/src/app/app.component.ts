import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { Observable } from 'rxjs';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { map } from 'rxjs/operators';
import { ProgressBarService } from './core/service/progress-bar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit, AfterViewChecked {

  private _loading = false;
  public get loading(): boolean {
    return this._loading;
  }

  title = 'PIMQuatro';

  isHandset$: Observable<boolean> = this._breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(
    private _breakpointObserver: BreakpointObserver,
    private _progress: ProgressBarService,
  ) { }

  ngOnInit() {
    this._progress.displaySubject.subscribe(value => this._loading = value);
  }

  ngAfterViewChecked() {
  }

}
