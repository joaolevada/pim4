import Navigo from 'navigo';

async function getView(url, element) {
  doc(element).html('<div class="progress pb-5"> <div class="progress-bar "></div></div> </div>');

  try {
    const res = await fetch(url);
    const viewContent = await (res.ok ? res.text() : null);

    doc(element).html(viewContent);
  } catch (err) {
    doc(element).html(`<h3 class="text-center mt-5">Houve um erro ao carregar o conteudo <br> Provavelmente você esta offline :/ <br> ${err}</h3>`);
  }
}

const router = new Navigo(null, true, '#!');

// Declaração das nossas rotas e suas respectivas views
router.on({
  'novo-chamado': async () => {
    getView('./src/views/abrirChamado/abrirChamado.html', '#app');

    // const { AbrirChamado } = await import('../views/abrirChamado/abrirChamado');
  },
  'encerrar-chamado': () => {
    getView('./src/views/encerrarChamado/encerrarChamado.html', '#app');
  },
  'novo-cliente': () => {
    doc('#app').html('<bread-crumb></bread-crumb> <novo-cliente></novo-cliente>');
  },
  home: () => {
    getView('./src/views/home/home.html', '#app');
  },
});

// rota principal
router.on(() => getView('./src/views/home/home.html', '#app'));

// getView com o template do 404

router.notFound(query => getView('./src/views/erro/erro.html', '#app'));

router.resolve();
