package br.com.on.fiap.hexagono.datapool;

import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;

public class DataPoolClienteRespostaDTO {

    private DataPoolClienteRespostaDTO() {}

    public static ClienteRespostaDTO clienteExistente(Long id) {
        ClienteRespostaDTO cliente = new ClienteRespostaDTO();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }

    public static ClienteRespostaDTO clienteComCpf(String cpf) {
        ClienteRespostaDTO cliente = new ClienteRespostaDTO();
        cliente.setId(1L);
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static ClienteRespostaDTO clienteValido() {
        ClienteRespostaDTO cliente = new ClienteRespostaDTO();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
