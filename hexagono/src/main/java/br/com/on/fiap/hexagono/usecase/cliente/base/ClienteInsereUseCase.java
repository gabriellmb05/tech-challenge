package br.com.on.fiap.hexagono.usecase.cliente.base;

import br.com.on.fiap.hexagono.usecase.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecase.dto.ClienteSaidaDTO;

public interface ClienteInsereUseCase {
    ClienteSaidaDTO inserir(ClienteEntradaDTO cliente);
}
