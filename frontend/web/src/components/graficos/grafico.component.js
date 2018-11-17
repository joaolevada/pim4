import { Slim } from 'slim-js';
import { tag, template } from 'slim-js/Decorators';
import Chart from 'chart.js';

const tpl = require('./grafico.component.html');

@tag('graficos-home')
@template(tpl)

class Grafico extends Slim {
  onRender() {

    // pega a largura da resolução da tela

    if (window.innerWidth > 768) {
      this.pie = false;
    } else {
      this.pie = true;
    }

    window.addEventListener('resize', () => {
      const width = window.innerWidth;

      if (width > 768) {
        this.pie = false;
      } else {
        this.pie = true;
      }
    });

    const ctx = this.lineChart.getContext('2d');

    Chart.defaults.global.animationSteps = 50;
    Chart.defaults.global.tooltipYPadding = 16;
    Chart.defaults.global.tooltipCornerRadius = 0;
    Chart.defaults.global.tooltipTitleFontStyle = 'normal';
    Chart.defaults.global.tooltipFillColor = 'rgba(0,160,0,0.8)';
    Chart.defaults.global.animationEasing = 'easeOutBounce';
    Chart.defaults.global.responsive = true;
    Chart.defaults.global.scaleLineColor = 'black';
    Chart.defaults.global.scaleFontSize = 16;

    const lineChart = new Chart(ctx, {

      type: 'line',

      data: {
        pointDotRadius: 10,
        bezierCurve: false,
        scaleShowVerticalLines: false,
        scaleGridLineColor: 'black',
        labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Jullho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
        datasets: [{
          label: 'Chamados Positivos',
          // backgroundColor: 'rgb(0,76,140, 0.4)',
          borderColor: 'rgb(0,76,140)',
          data: [0, 5, 10, 20, 30, 35, 45, 40, 50, 48, 43, 45],
        },
        {
          label: 'Chamados Negativos',
          // backgroundColor: 'rgb(88,165,240)',
          borderColor: 'rgb(88,165,240  )',
          data: [0, 20, 4, 10, 17, 25, 50, 45, 38, 29, 26, 20],
        },
        ],
      },

      options: {
        responsive: true,
        title: {
          display: true,
          text: 'Chamados Positivos vs Chamados Negativos',
          fontSize: 18,
          padding: 10,
        },
        legend: {
          position: 'bottom',
          labels: {
            fontSize: 12,
          },
        },
      },
    });

    const ctxp = this.pieChart.getContext('2d');

    Chart.defaults.global.animationSteps = 50;
    Chart.defaults.global.tooltipYPadding = 16;
    Chart.defaults.global.tooltipCornerRadius = 0;
    Chart.defaults.global.tooltipTitleFontStyle = 'normal';
    Chart.defaults.global.tooltipFillColor = 'rgba(0,160,0,0.8)';
    Chart.defaults.global.animationEasing = 'easeOutBounce';
    Chart.defaults.global.responsive = true;
    Chart.defaults.global.scaleLineColor = 'black';
    Chart.defaults.global.scaleFontSize = 16;

    const pieChart = new Chart(ctxp, {

      type: 'doughnut',

      data: {
        pointDotRadius: 10,
        bezierCurve: false,
        scaleShowVerticalLines: false,
        scaleGridLineColor: 'black',

        labels: [' Chamados Positivos', 'Chamados Negativos'],

        datasets: [{
          label: 'Chamados Positivos',
          backgroundColor: ['rgb(0,76,140,0.9)', 'rgb(88,165,240  )'],
          borderColor: ['rgb(0,76,140,0.9)', 'rgb(88,165,240  )'],
          data: [371, 284],
        },
        ],
      },

      options: {
        responsive: true,
        title: {
          display: true,
          text: 'Positivos vs Negativos ( Anual )',
          fontSize: 18,
          padding: 10,
        },
        legend: {
          position: 'bottom',
          labels: {
            fontSize: 12,
          },
        },
      },
    });
  }
}


export default Grafico;
