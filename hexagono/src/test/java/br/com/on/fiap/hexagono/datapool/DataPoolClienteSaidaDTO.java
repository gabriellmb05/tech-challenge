package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;

public class DataPoolClienteSaidaDTO {

    private DataPoolClienteSaidaDTO() {}

    public static ClienteSaidaDTO clienteExistente(Long id) {
        ClienteSaidaDTO cliente = new ClienteSaidaDTO();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }

    public static ClienteSaidaDTO clienteComCpf(String cpf) {
        ClienteSaidaDTO cliente = new ClienteSaidaDTO();
        cliente.setId(1L);
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static ClienteSaidaDTO clienteValido() {
        ClienteSaidaDTO cliente = new ClienteSaidaDTO();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
