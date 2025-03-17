package br.com.on.fiap.core.application.usecase.cliente;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import br.com.on.fiap.core.domain.Cliente;

public interface ClienteInsereUseCase {
    Cliente inserir(ClienteEntrada cliente);
}
