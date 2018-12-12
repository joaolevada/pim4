async function index() { /* eslint-disable */
  window.$ = window.jQuery = doc; // ligando o jquery ao Window, se vc estava procurando o app.js , ele fica em src
  window.Bootstrap = require('bootstrap');

  doc(document).ready(() => {
    doc('#fontZoom').on('click', (e) => {
      if (e.target.textContent == 'A+') {
        document.querySelector('html').style.fontSize = '17.3px'
        e.target.textContent = 'A-';
      } else {
        document.querySelector('html').style.fontSize = '16px'
        e.target.textContent = 'A+';
      }
    });
    doc('#sidebarCollapse').on('click', () => {
      doc('#sidebar').toggleClass('active');
      if (doc('#toRigth').attr('class') == 'svg-inline--fa fa-chevron-left fa-w-10 align-middle') {
        doc('#toRigth').toggleClass('fas fa-chevron-right');
      } else {
        doc('#toRigth').toggleClass('fas fa-chevron-left');
      }
    });

    doc('#signOut').on('click', () => {
      localStorage.setItem('user', '');
      localStorage.setItem('token', '');
    })
  });
}
index();
