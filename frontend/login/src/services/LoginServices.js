import { HttpServices } from "./httpServices";

export class LoginServices {
    static async getUser(email, senha) {
        const response = {};
        const token = `Basic ${btoa(`${email}:${senha}`)}`;
        const res = await HttpServices.get('http://localhost:8081/api/atendentes', token);
        if(res.status != '200'){ 
            response.msg = 'Email ou senha invalidos';
            response.status = false;
        } else {
            response.status = true;
            localStorage.setItem('token', token);
        }
        return response;
    }
}