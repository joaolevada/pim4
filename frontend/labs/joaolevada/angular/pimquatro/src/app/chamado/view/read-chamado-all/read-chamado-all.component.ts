import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { ChamadoService } from '../../service/chamado.service';
import { ChamadoResumoDto } from '../../model/chamado-resumo-dto';
import { ProgressBarService } from 'src/app/core/service/progress-bar.service';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

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

  isHandset$: Observable<boolean> = this._breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(
    private _breakpointObserver: BreakpointObserver,
    private _chamado: ChamadoService,
    private _progress: ProgressBarService,
  ) { }

  private async _carregarChamados() {
    this._progress.display();
    this._chamados = await this._chamado.buscar();
    console.log(this._chamados);
    this._progress.hide();
    return;
  }

  ngOnInit() {
    this._carregarChamados();
  }

}
