package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.domain.model.ClienteEntradaDTO;
import br.com.on.fiap.core.domain.model.ClienteRespostaDTO;

public interface ClienteController {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
