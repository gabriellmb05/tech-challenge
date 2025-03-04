package br.com.on.fiap.hexagono.usecase.cliente;

import br.com.on.fiap.hexagono.adapter.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.adapter.gateway.base.ClienteGateway;
import br.com.on.fiap.hexagono.domain.entity.Cliente;
import br.com.on.fiap.hexagono.domain.exception.ClienteNaoEncontradoExcecao;
import br.com.on.fiap.hexagono.domain.exception.message.MessageError;
import br.com.on.fiap.hexagono.util.FormatadorCpf;

public class ClienteBuscaPorCpfUseCase
        implements br.com.on.fiap.hexagono.usecase.cliente.base.ClienteBuscaPorCpfUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteBuscaPorCpfUseCase(ClienteGateway clienteGateway) {
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
