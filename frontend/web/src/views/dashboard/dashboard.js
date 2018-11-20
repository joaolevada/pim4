import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import { DashboardServices } from './services/DashboardServices';

import('../../components/graficos/grafico.component');

const tpl = require('./dashboard.html');

@tag('dash-board')
@template(tpl)

class Dashboard extends Slim {
  async onBeforeCreated() {
    const dashServices = new DashboardServices();
    this.response = await dashServices.read();
    this.data = await this.response.data;
    const abertos = this.data.filter(chamado => (!chamado.dataEncerramento ? chamado : null));
    const encerrados = this.data.filter(chamado => (chamado.dataEncerramento ? chamado : null));
    // console.log(encerrados.length);
    this.info = {
      total: this.data.length,
      abertos: abertos.length,
      encerrados: String(encerrados.length),
    };
    // console.log(this.data);
  }
}

export default Dashboard;
