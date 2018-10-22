package br.unip.ads.pim4.application.cliente.dto.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.domain.model.Cliente;

public final class ClienteDtoAssembler {
	
	public static ClienteResumoDto toDto (Cliente clienteCompleto) {
		return new ClienteResumoDto(clienteCompleto.getId().toString(), clienteCompleto.getPessoa().getNome(), clienteCompleto.getPessoa().getEmail().toString(), clienteCompleto.getPessoa().getCpf().toString());
	}
	
	public static Iterable<ClienteResumoDto> toDtoList (Iterable<Cliente> clientes) {
	
		ArrayList<ClienteResumoDto> dtoList = new ArrayList<>();
		
		for (Cliente clienteCompleto : clientes) {
			ClienteResumoDto dto = toDto(clienteCompleto); 
			dtoList.add(dto);
		}
		
		return dtoList;
		
	}

}
