package br.com.on.fiap.hexagono.interfaceadapters.interfaces.controller;

import br.com.on.fiap.hexagono.interfaceadapters.interfaces.dto.ClienteRespostaDTO;
import br.com.on.fiap.hexagono.usecases.dto.ClienteEntradaDTO;

public interface ClienteControlador {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
