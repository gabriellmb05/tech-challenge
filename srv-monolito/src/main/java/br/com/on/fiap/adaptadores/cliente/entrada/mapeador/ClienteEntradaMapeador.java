package br.com.on.fiap.adaptadores.cliente.entrada.mapeador;

import br.com.on.fiap.adaptadores.cliente.entrada.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.usecase.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.domain.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntradaMapeador {

    Cliente paraCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);

    ClienteEntradaDTO paraClienteDTO(ClienteSolicitacaoDTO clienteSolicaoDTO);

    default Cliente map(Long id) {
        return id == null ? null : new Cliente(id);
    }
}
