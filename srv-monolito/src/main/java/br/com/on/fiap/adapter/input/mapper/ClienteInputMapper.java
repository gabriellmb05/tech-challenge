package br.com.on.fiap.adapter.input.mapper;

import br.com.on.fiap.adapter.input.dto.request.ClienteSolicitacaoDTO;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteInputMapper {

    Cliente paraCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);

    ClienteEntradaDTO paraClienteDTO(ClienteSolicitacaoDTO clienteSolicaoDTO);

    default Cliente map(Long id) {
        return id == null ? null : new Cliente(id);
    }
}
