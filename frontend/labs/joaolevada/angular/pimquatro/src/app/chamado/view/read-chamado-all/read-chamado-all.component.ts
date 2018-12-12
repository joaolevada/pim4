import { Component, OnInit } from '@angular/core';
import { BreakpointObserver } from '@angular/cdk/layout';
import { ChamadoService } from '../../service/chamado.service';
import { ChamadoResumoDto } from '../../model/chamado-resumo-dto';
import { ProgressBarService } from 'src/app/core/service/progress-bar.service';

@Component({
  selector: 'app-read-chamado-all',
  templateUrl: './read-chamado-all.component.html',
  styleUrls: ['./read-chamado-all.component.sass'],
})
export class ReadChamadoAllComponent implements OnInit {

  private _chamados: ChamadoResumoDto[];
  public get chamados(): ChamadoResumoDto[] {
    return this._chamados;
  }

  constructor(
    private breakpointObserver: BreakpointObserver,
    private _chamado: ChamadoService,
    private _progress: ProgressBarService,
  ) { }

  private async _carregarChamados() {
    this._chamados = await this._chamado.buscar();
  }

  ngOnInit() {
    this._progress.display();
    this._carregarChamados();
    this._progress.hide();
  }

}
