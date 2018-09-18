import { Appdoc, doc } from '../../lib/lib'

export class AbrirChamado {

    constructor() {
        this._form = doc('#form') // Aqui ele ja instancia com o input que foi injetado pela rota
        this._texto

        this.teste()
    }

    teste() {

        Appdoc.on('submit', this._form, e => {

            e.preventDefault()

            this._texto = doc('#inputPassword').val()
            // debugger
            alert(this._texto)
        });
    }
}




