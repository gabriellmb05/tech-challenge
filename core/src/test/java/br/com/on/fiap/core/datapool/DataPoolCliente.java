package br.com.on.fiap.core.datapool;

import br.com.on.fiap.core.domain.entity.Cliente;

public class DataPoolCliente {

    private DataPoolCliente() {}

    public static Cliente clienteExistente(Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }

    public static Cliente clienteComCpf(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static Cliente clienteValido() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }

    public static Cliente clienteValidoSemId() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
