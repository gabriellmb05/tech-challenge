package br.com.on.fiap.core.application.usecase.cliente.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.util.FormatadorCpf;

public class ClienteBuscaPorCpfUseCaseImpl implements ClienteBuscaPorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteBuscaPorCpfUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente buscar(String cpf) {
        return clienteGateway
                .buscaClientePorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_CLIENTE_NAO_CADASTRADO.getMensagem(), FormatadorCpf.formatarCpf(cpf)));
    }
}
