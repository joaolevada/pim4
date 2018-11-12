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
  'novo-cliente': () => {
    doc('#app').html('<bread-crumb></bread-crumb> <novo-cliente></novo-cliente>');
  },
  home: () => {
    doc('#app').html('<home-teste></home-teste>');
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
  const userJson = '[{"id":"44eb2e73-d5e0-4a44-bef3-79a0f6e1e22e","nome":"Guilherme Revoredo","email":"guilherme11.gr@gmail.com"}]'
  localStorage.setItem('user',userJson);
  const user = JSON.parse(userJson);
  doc('#userlogado').html(user[0].nome);
  doc('#app').html('<home-teste></home-teste>')
});

// // getView com o template do 404

router.notFound(query => doc('#app').html('<bread-crumb></bread-crumb><erro-404></erro-404>'));

router.resolve();
