import Navigo from 'navigo'
import { doc } from '../lib/lib'

let getView = (url, element) => { 
    doc(element).html(`<div class="progress"> <div class="progress-bar "></div></div> </div>`)

    setTimeout(() => {

        fetch(url)
            .then(res => res.ok ? res.text() : null)
            .then(viewContent => doc(element).html( viewContent))
            .catch(err => doc(element).html( `<h3 class="text-center mt-5">Houve um erro ao carregar o conteudo <br> Provavelmente você esta offline :/ <br> ${err}</h3>`))

    }, 600)

}

const router = new Navigo(null, true, '#!')

// Declaração das nossas rotas e suas respectivas views
router.on({
    'novo-chamado': () => {
        getView('./src/views/abrirChamado/abrirChamado.html','#app')
    },
    'encerrar-chamado': () => {
        getView('./src/views/encerrarChamado/encerrarChamado.html', '#app')
    },
    'home': () => {
        getView('./src/views/home/home.html', '#app')
    }
})

// rota principal 
router.on(() => getView('./src/views/home/home.html', '#app'))

// getView com o template do 404

router.notFound(query => getView('./src/views/erro/erro.html', '#app'))

router.resolve()