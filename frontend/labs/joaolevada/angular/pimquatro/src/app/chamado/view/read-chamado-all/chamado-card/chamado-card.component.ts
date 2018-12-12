import { Component, OnInit, Input } from '@angular/core';
import { ChamadoResumoDto } from 'src/app/chamado/model/chamado-resumo-dto';

@Component({
  selector: 'app-chamado-card',
  templateUrl: './chamado-card.component.html',
  styleUrls: ['../read-chamado-all.component.sass']
})
export class ChamadoCardComponent implements OnInit {

  private _chamado: ChamadoResumoDto;
  public get chamado(): ChamadoResumoDto {
    return this._chamado;
  }
  @Input() public set chamado(v: ChamadoResumoDto) {
    this._chamado = v;
  }

  constructor() { }

  ngOnInit() {
  }

}
