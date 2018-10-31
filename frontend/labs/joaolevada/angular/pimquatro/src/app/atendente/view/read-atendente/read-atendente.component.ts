import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-read-atendente',
  templateUrl: './read-atendente.component.html',
  styleUrls: ['./read-atendente.component.sass'],
})
export class ReadAtendenteComponent implements OnInit {

  /** Based on the screen size, switch from standard to one column per row */
  cards = this._breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Card 1', cols: 1, rows: 1 },
          { title: 'Card 2', cols: 1, rows: 1 },
          { title: 'Card 3', cols: 1, rows: 1 },
          { title: 'Card 4', cols: 1, rows: 1 }
        ];
      }

      return [
        { title: 'Card 1', cols: 2, rows: 1 },
        { title: 'Card 2', cols: 1, rows: 1 },
        { title: 'Card 3', cols: 1, rows: 2 },
        { title: 'Card 4', cols: 1, rows: 1 }
      ];
    })
  );

  urlId: string;

  constructor(
    private _breakpointObserver: BreakpointObserver,
    private _route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this._route.params.subscribe((params: Params) => {
      this.urlId = params['id'];
    });

  }
}
