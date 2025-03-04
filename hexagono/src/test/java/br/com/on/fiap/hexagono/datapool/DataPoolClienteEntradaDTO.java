package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.usecase.dto.ClienteEntradaDTO;

public class DataPoolClienteEntradaDTO {

    private DataPoolClienteEntradaDTO() {}

    public static ClienteEntradaDTO clienteComCpf(String cpf) {
        ClienteEntradaDTO cliente = new ClienteEntradaDTO();
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static ClienteEntradaDTO clienteValido() {
        ClienteEntradaDTO cliente = new ClienteEntradaDTO();
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
