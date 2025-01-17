package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ClienteEntidade;
import br.com.on.fiap.hexagono.dominio.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteSaidaMapeador {

    Cliente paraCliente(ClienteEntidade clienteEntidade);

    ClienteEntidade paraEntidade(Cliente cliente);
}
