package br.com.on.fiap.hexagono.adapter.presenter;

import br.com.on.fiap.hexagono.usecase.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.usecase.dto.ClienteSaidaDTO;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
