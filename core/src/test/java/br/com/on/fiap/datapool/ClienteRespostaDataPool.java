package br.com.on.fiap.datapool;

import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;

public class ClienteRespostaDataPool {

    private ClienteRespostaDataPool() {}

    public static ClienteResposta criarClienteExistente(Long id) {
        ClienteResposta cliente = new ClienteResposta();
        cliente.setId(id);
        cliente.setNome("Cliente Exemplo");
        cliente.setEmail("cliente@exemplo.com");
        cliente.setCpf("12345678900");
        return cliente;
    }

    public static ClienteResposta criarClienteComCpf(String cpf) {
        ClienteResposta cliente = new ClienteResposta();
        cliente.setId(1L);
        cliente.setCpf(cpf);
        cliente.setNome("Maria Oliveira");
        cliente.setEmail("maria@exemplo.com");
        return cliente;
    }

    public static ClienteResposta criarClienteValido() {
        ClienteResposta cliente = new ClienteResposta();
        cliente.setId(1L);
        cliente.setCpf("12345678900");
        cliente.setNome("Carlos Souza");
        cliente.setEmail("carlos.souza@example.com");
        return cliente;
    }
}
