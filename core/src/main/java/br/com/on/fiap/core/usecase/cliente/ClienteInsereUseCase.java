package br.com.on.fiap.core.usecase.cliente;

import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import br.com.on.fiap.core.domain.model.Cliente;

public interface ClienteInsereUseCase {
    Cliente inserir(ClienteEntradaDTO cliente);
}
