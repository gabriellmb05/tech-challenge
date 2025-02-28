package br.com.on.fiap.hexagono.interfaceadapters.interfaces.presenter;

import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;

public interface ClienteApresentador {
    ClienteRespostaDTO formatar(ClienteSaidaDTO cliente);
}
