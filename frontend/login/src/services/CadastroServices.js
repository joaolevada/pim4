import { HttpServices } from "./httpServices";

export class CadastroServices {
    static async create(atendente) {
        const response = {};
        const res = await HttpServices.post('http://localhost:8080/api/atendentes', atendente);
        console.log(res);
        if(res.ok){ 
            response.msg = res.msg;
            response.status = res.ok;
        } else {
            response.msg = res.msg;
            response.status = res.ok;
        }
        return response;
    }
}