import br.com.pim4.domain.model.pessoa.Atendente;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
	// instaciando atendente	
	Atendente t1 = new Atendente();
	//cadastrando cliente
	t1.cadastrarCliente("446.531.63-.48","Fernando", "fernando@gmail.com", "9999-9999");
	t1.cadastrarCliente("123546", "Marcelo Mago", "Mago@gmail.com","33871818");
	
	//listando cliente
	t1.listarCliente();
	
	//obtercliente
	System.out.println(t1.obterCliente("123546"));
	
	//buscarCliente
	t1.buscarCliente("123546");
	
	//abrirChamado
	t1.abrirChamado("123546" ,"impressora parou", "No dia 02/10/2018 parou de funcionar a impressora"); // deu erro pq retorna um chamado
	
	}

}
