package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;

public class ClientePresenterImpl implements ClientePresenter {

    @Override
    public ClienteRespostaDTO formatar(Cliente cliente) {
        return ClienteRespostaDTO.fromDomain(cliente);
    }
}
