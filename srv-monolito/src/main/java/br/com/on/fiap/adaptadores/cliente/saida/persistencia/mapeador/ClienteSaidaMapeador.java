package br.com.on.fiap.adaptadores.cliente.saida.persistencia.mapeador;

import br.com.on.fiap.entidades.ClienteEntidade;
import br.com.on.fiap.hexagono.entidades.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteSaidaMapeador {

    @Mapping(target = "id", source = "cliId")
    @Mapping(target = "nome", source = "nmNome")
    @Mapping(target = "cpf", source = "nmCpf")
    @Mapping(target = "email", source = "nmEmail")
    @Mapping(target = "dataNascimento", source = "dhNascimento")
    Cliente paraCliente(ClienteEntidade clienteEntidade);

    @Mapping(target = "cliId", source = "id")
    @Mapping(target = "nmNome", source = "nome")
    @Mapping(target = "nmCpf", source = "cpf")
    @Mapping(target = "nmEmail", source = "email")
    @Mapping(target = "dhNascimento", source = "dataNascimento")
    ClienteEntidade paraEntidade(Cliente cliente);
}
