import Navigo from 'navigo';

const router = new Navigo(null, true, '#!');

// Declaração das nossas rotas e suas respectivas views
router.on({
  'novo-chamado': async () => {

    doc('#app').html('<bread-crumb></bread-crumb> <novo-chamado></novo-chamado>');
  },
  'visualizar-chamado': () => {

    doc('#app').html('<bread-crumb></bread-crumb> <visualizar-chamado></visualizar-chamado>');
  },
  'visualizar-chamado/:protocolo': (params) => {
    // alert(params.protocolo)
    doc('#app').html(`<bread-crumb></bread-crumb> <visualizar-chamado-id id="${params.protocolo}"></visualizar-chamado-id>`);
  },
  'novo-cliente': () => {
    doc('#app').html('<bread-crumb></bread-crumb> <novo-cliente></novo-cliente>');
  },
  'novo-atendente': () => {
    doc('#app').html('<bread-crumb></bread-crumb> <novo-atendente></novo-atendente>');
  },
  dashboard: () => {
    doc('#app').html('<dash-board></dash-board>');
  },
});

// rota principal
router.on(() => {
  // if(localStorage.getItem('token')) {
  //   const user = JSON.parse(localStorage.getItem('user'));
  //   doc('#userlogado').html(user[0].nome);
  //   doc('#app').html('<home-teste></home-teste>')
  // } else {
  //   window.location = 'http://localhost:3000/';
  // }
  const userJson = '[{"id":"3d737590-50bd-4362-9826-1bad5eacad37","nome":"Guilherme Revoredo","email":"guilherme11.gr@gmail.com"}]';
  localStorage.setItem('user', userJson);
  const user = JSON.parse(userJson);
  doc('#userlogado').html(`${user[0].nome} &#9207;`);
  doc('#app').html('<dash-board></dash-board>');
});

// // getView com o template do 404

router.notFound(query => doc('#app').html('<bread-crumb></bread-crumb><erro-404></erro-404>'));

router.resolve();
