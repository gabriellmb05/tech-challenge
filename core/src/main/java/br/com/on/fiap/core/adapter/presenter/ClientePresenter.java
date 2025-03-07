package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.resposta.cliente.ClienteResposta;
import br.com.on.fiap.core.domain.Cliente;

public interface ClientePresenter {
    ClienteResposta formatar(Cliente cliente);
}
