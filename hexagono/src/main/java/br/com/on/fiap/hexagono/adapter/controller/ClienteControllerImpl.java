package br.com.on.fiap.hexagono.adapter.controller;

import br.com.on.fiap.hexagono.adapter.controller.base.ClienteController;
import br.com.on.fiap.hexagono.adapter.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteSaidaDTO;
import br.com.on.fiap.hexagono.adapter.presenter.base.ClienteBasePresenter;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteBuscaPorCpfUseCase;
import br.com.on.fiap.hexagono.usecase.cliente.base.ClienteInsereUseCase;

public class ClienteControllerImpl implements ClienteController {

    private final ClienteInsereUseCase clienteInsereUseCase;
    private final ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase;
    private final ClienteBasePresenter clientePresenter;

    public ClienteControllerImpl(
            ClienteInsereUseCase clienteInsereUseCase,
            ClienteBuscaPorCpfUseCase clienteBuscaPorCpfUseCase,
            ClienteBasePresenter clientePresenter) {
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
