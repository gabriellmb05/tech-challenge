package br.com.on.fiap.core.usecase.cliente;

import br.com.on.fiap.core.application.dto.ClienteSaidaDTO;

public interface ClienteBuscaPorCpfUseCase {
    ClienteSaidaDTO buscar(String cpf);
}
