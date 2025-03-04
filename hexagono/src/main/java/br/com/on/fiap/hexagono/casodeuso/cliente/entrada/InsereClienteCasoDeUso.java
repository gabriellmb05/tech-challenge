package br.com.on.fiap.hexagono.casodeuso.cliente.entrada;

import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.casodeuso.cliente.dto.ClienteSaidaDTO;

public interface InsereClienteCasoDeUso {
    ClienteSaidaDTO inserir(ClienteEntradaDTO cliente);
}
