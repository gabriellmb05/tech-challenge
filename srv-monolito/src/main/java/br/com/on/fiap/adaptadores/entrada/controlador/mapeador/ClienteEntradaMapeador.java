package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.resposta.ClienteRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.solicitacao.ClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntradaMapeador {

    ClienteRespostaDTO paraClienteDTO(Cliente cliente);

    Cliente paraCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);
}
