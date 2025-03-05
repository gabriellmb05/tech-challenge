package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(Cliente cliente);
}
