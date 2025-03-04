package br.com.on.fiap.core.adapter.presenter;

import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;

public interface ClientePresenter {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
