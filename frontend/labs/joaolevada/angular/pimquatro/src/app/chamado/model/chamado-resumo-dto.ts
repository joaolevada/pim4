import { ClienteResumoDto } from './cliente-resumo-dto';
import { EventoChamadoDto } from './evento-chamado-dto';

export interface ChamadoResumoDto {

  assunto: string;
  cliente: ClienteResumoDto;
  dataAbertura: Date;
  dataEncerramento: Date;

  eventos: EventoChamadoDto[];

  protocolo: string;

}
