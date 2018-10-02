package br.com.pim4.domain.model.pessoa;

import java.util.ArrayList;
import java.util.List;

import br.com.pim4.domain.model.chamado.Chamado;

public class Atendente {
	
	private String cpf;
	private String email;
	private String Senha;
	private Cliente cliente;
	private Chamado chamado;
	public static List<Cliente>listadecliente = new ArrayList<Cliente>();
	
	

	
	
	// cadastrarCliente
	public void cadastrarCliente(String cpf,String nome,String email, String telefone) {
		cliente = new Cliente();
		cliente.cadastrarCliente(cpf, nome, email, telefone);
		cliente.listadecliente.add(cliente);
	}
	
	//obter cliente
	public Cliente obterCliente(String cpf) {
		return cliente.obterCliente(cpf);
	}
		
	// abrir chamado
	public void abrirChamado(Atendente this,String cpf,String assunto,String descricao ) {
		
		System.out.println(chamado.abrirChamado(assunto, descricao, this));
	}
	
	// buscarCliente
	public void buscarCliente(String cpf) {
		cliente.buscarCliente(cpf);
	}
	//
	public void listarCliente() {
		for(Object o:listadecliente) {
			System.out.println(o);
		}
	}
	
	
	 
}
