package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.dominio.Cliente;

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

    public static Cliente clienteInexistente(Long id) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }
}
