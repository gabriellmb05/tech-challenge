package br.com.on.fiap.hexagono.application.usecase.cliente.base;

import br.com.on.fiap.hexagono.application.dto.ClienteSaidaDTO;

public interface ClienteBuscaPorCpfUseCase {
    ClienteSaidaDTO buscar(String cpf);
}
