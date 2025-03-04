package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.ClienteEntradaDTO;
import br.com.on.fiap.core.application.dto.ClienteRespostaDTO;

public interface ClienteController {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
