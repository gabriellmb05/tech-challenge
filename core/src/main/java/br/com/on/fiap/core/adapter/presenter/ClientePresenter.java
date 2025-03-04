package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.model.Cliente;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(Cliente cliente);
}
