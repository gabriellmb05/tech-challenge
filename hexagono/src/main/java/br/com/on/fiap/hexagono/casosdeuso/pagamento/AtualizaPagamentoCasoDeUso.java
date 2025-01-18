package br.com.on.fiap.hexagono.casosdeuso.cliente;

import br.com.on.fiap.hexagono.dominio.Cliente;
import br.com.on.fiap.hexagono.excecao.ClienteExistenteExcecao;
import br.com.on.fiap.hexagono.excecao.message.MessageError;
import br.com.on.fiap.hexagono.portas.entrada.cliente.InsereClientePortaEntrada;
import br.com.on.fiap.hexagono.portas.saida.cliente.PersisteClientePortaSaida;

public class InsereClienteCasoDeUso implements InsereClientePortaEntrada {

    private final PersisteClientePortaSaida persisteClientePortaSaida;

    public InsereClienteCasoDeUso(PersisteClientePortaSaida persisteClientePortaSaida) {
        this.persisteClientePortaSaida = persisteClientePortaSaida;
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        persisteClientePortaSaida.buscaClientePorCpf(cliente.getCpf()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(MessageError.MSG_ERRO_CPF_JA_CADASTRADO.getMensagem(), cliente.getCpf());
        });

        persisteClientePortaSaida.buscaClientePorEmail(cliente.getEmail()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_EMAIL_JA_CADASTRADO.getMensagem(), cliente.getEmail());
        });
        return persisteClientePortaSaida.salvaCliente(cliente);
    }
}
