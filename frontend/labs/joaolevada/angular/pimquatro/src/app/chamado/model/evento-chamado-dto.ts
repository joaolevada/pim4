import { AtendenteResumoDto } from './atendente-resumo-dto';

export interface EventoChamadoDto {

  atendente: AtendenteResumoDto;
  data: Date;
  descricao: string;
  tipo: string;

}
