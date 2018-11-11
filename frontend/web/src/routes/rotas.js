import Navigo from 'navigo';

/*
async function getView(url, element) {
  doc(element).html('<div class="progress pb-5"> <div class="progress-bar "></div></div> </div>');

  try {
    const res = await fetch(url);
    const viewContent = await (res.ok ? res.text() : null);

    doc(element).html(viewContent);
  } catch (err) {
doc(element)
.html(`<h3 class="text-center mt-5">Houve um erro ao carregar o conteudo <br>
Provavelmente você esta offline :/ <br> ${err}</h3>`);
  }
}
*/

const router = new Navigo(null, true, '#!');

// Declaração das nossas rotas e suas respectivas views
router.on({
  'novo-chamado': async () => {

    doc('#app').html('<bread-crumb></bread-crumb> <novo-chamado></novo-chamado>');
  },
  // 'encerrar-chamado': () => {

  //   doc('#app').html('<bread-crumb></bread-crumb> <encerrar-chamado></encerrar-chamado>');
  // },
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
