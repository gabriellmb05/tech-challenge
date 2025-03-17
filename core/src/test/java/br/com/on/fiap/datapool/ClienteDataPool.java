package br.com.on.fiap.datapool;

import br.com.on.fiap.core.domain.Cliente;

public class ClienteDataPool {

    private ClienteDataPool() {}

    public static Cliente criarClienteExistente(Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }

    public static Cliente criarClienteComCpf(String cpf) {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static Cliente criarClienteValido() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }

    public static Cliente criarClienteValidoSemId() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
