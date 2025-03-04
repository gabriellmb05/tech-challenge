package br.com.on.fiap.hexagono.application.usecase.cliente;

import br.com.on.fiap.hexagono.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;

public interface ClienteInsereUseCase {
    ClienteSaidaDTO inserir(ClienteEntradaDTO cliente);
}
