import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { AtendenteService } from '../../service/atendente.service';
import { AtendenteResumoDto } from '../../model/atendente-resumo-dto';

@Component({
  selector: 'app-read-atendente-all',
  templateUrl: './read-atendente-all.component.html',
  styleUrls: ['./read-atendente-all.component.sass'],
})
export class ReadAtendenteAllComponent implements OnInit {

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

  atendentes: AtendenteResumoDto[];

  constructor(
    private _breakpointObserver: BreakpointObserver,
    private _service: AtendenteService
  ) { }

  ngOnInit(): void {
    this._carregarAtendentes();
  }

  private _carregarAtendentes(): Promise<void> {
    return this._service.buscar().then(response => {
      this.atendentes = response;
      return Promise.resolve();
    });
  }

}
