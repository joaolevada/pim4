async function index() { /* eslint-disable */
  window.$ = window.jQuery = doc; // ligando o jquery ao Window, se vc estava procurando o app.js , ele fica em src
  window.Bootstrap = require('bootstrap');

  doc(document).ready(() => {
    doc('#sidebarCollapse').on('click', () => {

      doc('#sidebar').toggleClass('active');

      if (doc('#toRigth').attr('class') == 'svg-inline--fa fa-chevron-left fa-w-10 align-middle')
        doc('#toRigth').toggleClass('fas fa-chevron-right');

      else doc('#toRigth').toggleClass('fas fa-chevron-left');

    });
  });
}
index();
