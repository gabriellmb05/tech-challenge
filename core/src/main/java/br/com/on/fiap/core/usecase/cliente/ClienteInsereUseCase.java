package br.com.on.fiap.core.usecase.cliente;

import br.com.on.fiap.core.domain.model.Cliente;
import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;

public interface ClienteInsereUseCase {
    Cliente inserir(ClienteEntradaDTO cliente);
}
