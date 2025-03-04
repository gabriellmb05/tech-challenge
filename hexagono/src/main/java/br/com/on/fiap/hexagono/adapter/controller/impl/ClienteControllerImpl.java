package br.com.on.fiap.hexagono.adapter.controller.impl;

import br.com.on.fiap.hexagono.adapter.controller.ClienteController;
import br.com.on.fiap.hexagono.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.ClientePresenter;
import br.com.on.fiap.hexagono.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.hexagono.application.usecase.cliente.ClienteInsereUseCase;

public class ClienteControllerImpl implements ClienteController {

    private final ClienteInsereUseCase clienteInsereUseCase;
    private final ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase;
    private final ClientePresenter clientePresenter;

    public ClienteControllerImpl(
            ClienteInsereUseCase clienteInsereUseCase,
            ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase,
            ClientePresenter clientePresenter) {
        this.clienteInsereUseCase = clienteInsereUseCase;
        this.clienteBuscaPorCpfUseCase = clienteBuscaPorCpfUseCase;
        this.clientePresenter = clientePresenter;
    }

    @Override
    public ClienteRespostaDTO buscaClientePorCpf(String cpf) {
        ClienteSaidaDTO clienteSaidaDTO = clienteBuscaPorCpfUseCase.buscar(cpf);
        return clientePresenter.formatar(clienteSaidaDTO);
    }

    @Override
    public ClienteRespostaDTO insereCliente(ClienteEntradaDTO clienteEntradaDTO) {
        ClienteSaidaDTO clienteSaidaDTO = clienteInsereUseCase.inserir(clienteEntradaDTO);
        return clientePresenter.formatar(clienteSaidaDTO);
    }
}
