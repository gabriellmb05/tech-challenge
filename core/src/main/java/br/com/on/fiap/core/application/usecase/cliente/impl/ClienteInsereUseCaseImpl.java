package br.com.on.fiap.core.application.usecase.cliente.impl;

import br.com.on.fiap.core.adapter.gateway.ClienteGateway;
import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.core.domain.entity.Cliente;
import br.com.on.fiap.core.domain.exception.ClienteExistenteExcecao;
import br.com.on.fiap.core.domain.exception.message.MessageError;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;
import br.com.on.fiap.core.util.FormatadorCpf;

public class ClienteInsereUseCaseImpl implements ClienteInsereUseCase {

    private final ClienteGateway clienteGateway;

    public ClienteInsereUseCaseImpl(ClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public ClienteSaidaDTO inserir(ClienteEntradaDTO clienteEntradaDTO) {
        Cliente cliente = new Cliente(
                clienteEntradaDTO.getCpf(),
                clienteEntradaDTO.getNome(),
                clienteEntradaDTO.getEmail(),
                clienteEntradaDTO.getDataNascimento());
        clienteGateway.buscaClientePorCpf(cliente.getCpf()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_CPF_JA_CADASTRADO.getMensagem(),
                    FormatadorCpf.formatarCpf(cliente.getCpf()));
        });

        clienteGateway.buscaClientePorEmail(cliente.getEmail()).ifPresent(p -> {
            throw new ClienteExistenteExcecao(
                    MessageError.MSG_ERRO_CLIENTE_EMAIL_JA_CADASTRADO.getMensagem(), cliente.getEmail());
        });
        Cliente clientePersistido = clienteGateway.salvaCliente(cliente);
        return new ClienteSaidaDTO(
                clientePersistido.getId(),
                clientePersistido.getNome(),
                clientePersistido.getCpf(),
                clientePersistido.getEmail(),
                clientePersistido.getDataNascimento());
    }
}
