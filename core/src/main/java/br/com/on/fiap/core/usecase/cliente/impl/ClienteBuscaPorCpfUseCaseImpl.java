package br.com.on.fiap.core.usecase.cliente.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.core.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.domain.entity.Cliente;
import br.com.on.fiap.core.domain.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import br.com.on.fiap.core.util.FormatadorCpf;

public class ClienteBuscaPorCpfUseCaseImpl implements ClienteBuscaPorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteBuscaPorCpfUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteSaidaDTO buscar(String cpf) {
        Cliente cliente = clienteGateway
                .buscaClientePorCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoExcecao(
                        MessageError.MSG_ERRO_CLIENTE_NAO_CADASTRADO.getMensagem(), FormatadorCpf.formatarCpf(cpf)));
        return new ClienteSaidaDTO(
                cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataNascimento());
    }
}
