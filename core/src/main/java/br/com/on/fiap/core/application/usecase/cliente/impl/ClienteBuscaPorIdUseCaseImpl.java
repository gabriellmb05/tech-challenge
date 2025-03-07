package br.com.on.fiap.core.application.usecase.cliente.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.application.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.application.exception.message.MessageError;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorIdUseCase;
import br.com.on.fiap.core.domain.Cliente;

public class ClienteBuscaPorIdUseCaseImpl implements ClienteBuscaPorIdUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteBuscaPorIdUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente buscar(Long id) {
        return clienteGateway
                .buscaClientePorId(id)
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_CLIENTE_NAO_ENCONTRATO_PARA_ID.getMensagem(), id));
    }
}
