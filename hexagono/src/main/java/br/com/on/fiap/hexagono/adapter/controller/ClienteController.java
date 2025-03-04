package br.com.on.fiap.hexagono.adapter.controller;

import br.com.on.fiap.hexagono.usecase.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.usecase.dto.ClienteRespostaDTO;

public interface ClienteController {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
