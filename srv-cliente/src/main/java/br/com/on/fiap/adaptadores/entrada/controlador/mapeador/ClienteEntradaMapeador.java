package br.com.on.fiap.adaptadores.entrada.controlador.mapeador;

import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteRespostaDTO;
import br.com.on.fiap.adaptadores.entrada.controlador.dto.ClienteSolicitacaoDTO;
import br.com.on.fiap.hexagono.dominio.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntradaMapeador {

  ClienteRespostaDTO paraClienteDTO(Cliente cliente);

  Cliente paraCliente(ClienteSolicitacaoDTO clienteSolicitacaoDTO);
}
