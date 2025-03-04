package br.com.on.fiap.core.adapter.presenter.impl;

import br.com.on.fiap.core.adapter.presenter.ClientePresenter;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.model.Cliente;

public class ClientePresenterImpl implements ClientePresenter {

    @Override
    public ClienteRespostaDTO formatar(Cliente cliente) {
        return new ClienteRespostaDTO(
                cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getDataNascimento());
    }
}
