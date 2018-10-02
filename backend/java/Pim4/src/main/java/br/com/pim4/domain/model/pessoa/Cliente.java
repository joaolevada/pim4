package br.com.pim4.domain.model.pessoa;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private String sobrenome;
	private String cep;
	private String endereco;
	private String bairro;
	private String numero;
	private String cidade;
	private String Estado;
	private String Celular;
	public static List<Cliente>listadecliente = new ArrayList<Cliente>();


	
	public Cliente() {
		
	}
	
	public Cliente(String cpf) {
		this.cpf = cpf;
	}
	public void cadastrarCliente(String cpf, String nome,String email,String telefone) {
		this.cpf =cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente obterCliente(String cpf) {
		Cliente c1 = new Cliente(cpf);
		if(listadecliente.contains(c1)) {
		int index = listadecliente.indexOf(c1);
		c1 = (Cliente)listadecliente.get(index);
		}else {
				c1 = null;
			}	
		return c1;
	}
	public void buscarCliente(String cpf) {
		Cliente c1 = new Cliente(cpf);
		if(listadecliente.contains(c1)) {
		int index = listadecliente.indexOf(c1);
		c1 = (Cliente)listadecliente.get(index);
		}else {
				c1 = null;
			}	
		
			System.out.println(c1);
	}

	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCelular() {
		return Celular;
	}
	public void setCelular(String celular) {
		Celular = celular;
	}
	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
	
}
