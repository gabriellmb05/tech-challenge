package br.com.on.fiap.core.application.usecase.cliente.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import br.com.on.fiap.core.application.exception.ClienteExistenteExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.domain.Cliente;
import br.com.on.fiap.core.util.FormatadorCpf;

public class ClienteInsereUseCaseImpl implements ClienteInsereUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteInsereUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente inserir(ClienteEntrada clienteEntrada) {
        Cliente cliente = new Cliente(
                clienteEntrada.getCpf(),
                clienteEntrada.getNome(),
                clienteEntrada.getEmail(),
                clienteEntrada.getDataNascimento());
        clienteGateway.buscaClientePorCpf(cliente.getCpf()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_CPF_JA_CADASTRADO.getMensagem(),
                    FormatadorCpf.formatarCpf(cliente.getCpf()));
        });

        clienteGateway.buscaClientePorEmail(cliente.getEmail()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_EMAIL_JA_CADASTRADO.getMensagem(), cliente.getEmail());
        });
        return clienteGateway.salvaCliente(cliente);
    }
}
