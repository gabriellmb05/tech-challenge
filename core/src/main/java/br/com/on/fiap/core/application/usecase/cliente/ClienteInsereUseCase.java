package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.core.domain.entity.Cliente;

public interface ClienteInsereUseCase {
    Cliente inserir(ClienteEntradaDTO cliente);
}
