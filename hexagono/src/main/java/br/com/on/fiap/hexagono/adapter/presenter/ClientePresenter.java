package br.com.on.fiap.hexagono.adapter.presenter;

import br.com.on.fiap.hexagono.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
