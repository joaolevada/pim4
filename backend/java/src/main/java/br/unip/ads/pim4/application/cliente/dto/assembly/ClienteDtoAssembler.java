package br.unip.ads.pim4.application.cliente.dto.assembly;

import java.util.ArrayList;
import java.util.Objects;

import br.unip.ads.pim4.application.cliente.dto.ClienteResumoDto;
import br.unip.ads.pim4.domain.model.Cliente;

public final class ClienteDtoAssembler {

	public static ClienteResumoDto toDto(Cliente clienteCompleto) {
		
		String movel = Objects.isNull(clienteCompleto.getTelefoneMovel()) ? null : clienteCompleto.getTelefoneMovel().asString();
		String fixo = Objects.isNull(clienteCompleto.getTelefoneFixo()) ? null : clienteCompleto.getTelefoneFixo().asString();
		
		return new ClienteResumoDto(clienteCompleto.getId().asString(), clienteCompleto.getPessoa().getNome(),
				clienteCompleto.getPessoa().getEmail().asString(), clienteCompleto.getPessoa().getCpf().asString(),
				movel, fixo);
	}

	public static Iterable<ClienteResumoDto> toDtoList(Iterable<Cliente> clientes) {

		ArrayList<ClienteResumoDto> dtoList = new ArrayList<>();

		for (Cliente clienteCompleto : clientes) {
			ClienteResumoDto dto = toDto(clienteCompleto);
			dtoList.add(dto);
		}

		return dtoList;

	}

}
