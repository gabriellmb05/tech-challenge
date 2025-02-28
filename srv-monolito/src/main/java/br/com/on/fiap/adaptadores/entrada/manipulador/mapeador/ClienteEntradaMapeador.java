package br.com.on.fiap.adaptadores.entrada.manipulador.mapeador;

import br.com.on.fiap.adaptadores.entrada.manipulador.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.entities.entidades.Cliente;
import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntradaMapeador {

    Cliente paraCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);

    ClienteEntradaDTO paraClienteDTO(ClienteSolicitacaoDTO clienteSolicaoDTO);

    default Cliente map(Long id) {
        return id == null ? null : new Cliente(id);
    }
}
