package br.com.on.fiap.hexagono.usecases.interfaces.entrada.cliente;

import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteSaidaDTO;

public interface InsereClienteCasoDeUso {
    ClienteSaidaDTO inserir(ClienteEntradaDTO cliente);
}
