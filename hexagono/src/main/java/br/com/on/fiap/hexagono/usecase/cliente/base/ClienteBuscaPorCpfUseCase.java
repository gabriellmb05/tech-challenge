package br.com.on.fiap.hexagono.usecase.cliente.base;

import br.com.on.fiap.hexagono.usecase.dto.ClienteSaidaDTO;

public interface ClienteBuscaPorCpfUseCase {
    ClienteSaidaDTO buscar(String cpf);
}
