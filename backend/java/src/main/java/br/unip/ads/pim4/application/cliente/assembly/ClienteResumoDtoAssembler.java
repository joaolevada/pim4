package br.unip.ads.pim4.application.cliente.assembly;

import java.util.ArrayList;

import br.unip.ads.pim4.application.cliente.ClienteResumoDto;
import br.unip.ads.pim4.domain.model.Cliente;

public final class ClienteResumoDtoAssembler {
	
	public static ClienteResumoDto toDto (Cliente cliente) {
		return new ClienteResumoDto(cliente.getId().toString(), cliente.getPessoa().getNome(), cliente.getPessoa().getEmail().toString(), cliente.getPessoa().getCpf().toString());
	}
	
	public static Iterable<ClienteResumoDto> toDtoList (Iterable<Cliente> clientes) {
	
		ArrayList<ClienteResumoDto> dtoList = new ArrayList<>();
		
		for (Cliente c : clientes) {
			ClienteResumoDto dto = toDto(c); 
			dtoList.add(dto);
		}
		
		return dtoList;
		
	}

}
