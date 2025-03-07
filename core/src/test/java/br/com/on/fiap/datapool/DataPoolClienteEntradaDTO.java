package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;

public class DataPoolClienteEntradaDTO {

    private DataPoolClienteEntradaDTO() {}

    public static ClienteEntrada clienteComCpf(String cpf) {
        ClienteEntrada cliente = new ClienteEntrada();
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static ClienteEntrada clienteValido() {
        ClienteEntrada cliente = new ClienteEntrada();
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
