package br.com.on.fiap.adaptadores.saida.persistencia.mapeador;

import br.com.on.fiap.adaptadores.saida.persistencia.entidade.ClienteEntidade;
import br.com.on.fiap.hexagono.dominio.Cliente;
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

    @Mapping(target = "nmNome", source = "nome")
    @Mapping(target = "nmCpf", source = "cpf")
    @Mapping(target = "nmEmail", source = "email")
    @Mapping(target = "dhNascimento", source = "dataNascimento")
    ClienteEntidade paraEntidade(Cliente cliente);
}
