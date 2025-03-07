package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.ClienteRespostaDTO;
import br.com.on.fiap.core.domain.Cliente;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(Cliente cliente);
}
