package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import br.com.on.fiap.core.application.dto.resposta.ClienteRespostaDTO;

public interface ClienteController {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntrada cliente);
}
