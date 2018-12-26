import { Component, OnInit } from '@angular/core';
import { AtendenteService } from '../../service/atendente.service';
import { AtendenteResumoDto } from '../../model/atendente-resumo-dto';
import { ProgressBarService } from 'src/app/core/service/progress-bar.service';

@Component({
  selector: 'app-read-atendente-all',
  templateUrl: './read-atendente-all.component.html',
  styleUrls: ['./read-atendente-all.component.sass'],
})
export class ReadAtendenteAllComponent implements OnInit {

  atendentes: AtendenteResumoDto[];

  constructor(
    private _atendente: AtendenteService,
    private _progress: ProgressBarService,
  ) { }

  ngOnInit(): void {
    this._progress.display();
    this._carregarAtendentes();
    // this._progress.hide();
  }

  private async _carregarAtendentes() {
    throw new Error('AINDA NÃO IMPLEMENTADO!!');
  }

}
