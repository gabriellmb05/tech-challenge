package br.com.on.fiap.adapter.output.persistence.mapper;

import br.com.on.fiap.adapter.output.persistence.entity.ClienteEntity;
import br.com.on.fiap.core.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteSaidaMapper {

    @Mapping(target = "id", source = "cliId")
    @Mapping(target = "nome", source = "nmNome")
    @Mapping(target = "cpf", source = "nmCpf")
    @Mapping(target = "email", source = "nmEmail")
    @Mapping(target = "dataNascimento", source = "dhNascimento")
    Cliente paraCliente(ClienteEntity clienteEntity);

    @Mapping(target = "cliId", source = "id")
    @Mapping(target = "nmNome", source = "nome")
    @Mapping(target = "nmCpf", source = "cpf")
    @Mapping(target = "nmEmail", source = "email")
    @Mapping(target = "dhNascimento", source = "dataNascimento")
    ClienteEntity paraEntidade(Cliente cliente);
}
