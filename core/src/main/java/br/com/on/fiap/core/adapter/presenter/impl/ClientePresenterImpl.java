package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;
import br.com.on.fiap.core.domain.Cliente;

public class ClientePresenterImpl implements ClientePresenter {

    @Override
    public ClienteResposta formatar(Cliente cliente) {
        return ClienteResposta.fromDomain(cliente);
    }
}
