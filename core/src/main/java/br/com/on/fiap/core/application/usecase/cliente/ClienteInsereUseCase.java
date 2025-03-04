package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;

public interface ClienteInsereUseCase {
    ClienteSaidaDTO inserir(ClienteEntradaDTO cliente);
}
