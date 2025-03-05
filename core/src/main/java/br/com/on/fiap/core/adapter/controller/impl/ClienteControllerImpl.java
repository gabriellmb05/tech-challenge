package br.com.on.fiap.core.adapter.controller.impl;

import br.com.on.fiap.core.adapter.controller.ClienteController;
import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.application.usecase.cliente.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.core.application.usecase.cliente.ClienteInsereUseCase;

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
        Cliente cliente = clienteBuscaPorCpfUseCase.buscar(cpf);
        return clientePresenter.formatar(cliente);
    }

    @Override
    public ClienteRespostaDTO insereCliente(ClienteEntradaDTO clienteEntradaDTO) {
        Cliente cliente = clienteInsereUseCase.inserir(clienteEntradaDTO);
        return clientePresenter.formatar(cliente);
    }
}
