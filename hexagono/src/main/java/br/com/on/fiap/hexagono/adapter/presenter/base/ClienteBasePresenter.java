package br.com.on.fiap.hexagono.adapter.presenter.base;

import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteSaidaDTO;

public interface ClienteBasePresenter {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
