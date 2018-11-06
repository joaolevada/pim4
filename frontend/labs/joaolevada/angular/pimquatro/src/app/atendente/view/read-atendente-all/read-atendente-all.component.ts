import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { AtendenteService } from '../../service/atendente.service';
import { AtendenteResumoDto } from '../../model/atendente-resumo-dto';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-read-atendente-all',
  templateUrl: './read-atendente-all.component.html',
  styleUrls: ['./read-atendente-all.component.sass'],
})
export class ReadAtendenteAllComponent implements OnInit {

  atendentes: Observable<AtendenteResumoDto[]>;

  constructor(
    private _service: AtendenteService
  ) { }

  ngOnInit(): void {
    this._carregarAtendentes();
  }

  private _carregarAtendentes() {
    this.atendentes = this._service.buscar();
  }

}
