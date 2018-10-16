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

router.on('/app', () => {
  doc('main').html('<app-layout></app-layout>');
  // router.pause();
  // router.navigate('/#!home');
  // router.resume(); // or .pause(false)
},
{
  before(done, params) {
    // doing some async operation
    done();
  },
  after(params) {
    setTimeout(() => {
      router.pause();
      router.navigate('/app/home'); // /#!/app/home /#!/app/home
      router.resume(); // or .pause(false)
    }, 100);
  },
  leave(params) {
    // when you are going out of the that route
  },
});

router.on({
  'app/novo-chamado': async () => {

    doc('#app').html('<bread-crumb></bread-crumb> <novo-chamado></novo-chamado>');
  },
  'app/encerrar-chamado': () => {

    doc('#app').html('<bread-crumb></bread-crumb> <encerrar-chamado></encerrar-chamado>');
  },
  'app/novo-cliente': () => {
    doc('#app').html('<bread-crumb></bread-crumb> <novo-cliente></novo-cliente>');
  },
  'app/home': () => {
    doc('#app').html('<home-teste></home-teste>');
  },
});


// rota principal
router.on(() => doc('main').html('<app-login></app-login>'));

// // getView com o template do 404

router.notFound(query => doc('#app').html('<bread-crumb></bread-crumb><erro-404></erro-404>'));

router.resolve();
