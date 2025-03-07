package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.application.dto.resposta.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.Cliente;

public class ClientePresenterImpl implements ClientePresenter {

    @Override
    public ClienteRespostaDTO formatar(Cliente cliente) {
        return ClienteRespostaDTO.fromDomain(cliente);
    }
}
