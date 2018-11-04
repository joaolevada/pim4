import { HttpServices } from "./httpServices";

export class CadastroServices {
    static async create(atendente) {
        const response = {};
        const res = await HttpServices.post('http://localhost:8081/api/atendentes', atendente);
        if(res.status == '201'){ 
            response.msg = 'Cadastro efetuado com sucesso !';
            response.status = res.ok;
        } else {
            response.msg = 'Cadastrao nao pode ser efetudado com sucesso !';
            response.status = false;
        }
        return response;
    }
}