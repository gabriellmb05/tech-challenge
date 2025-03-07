package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.ClienteEntrada;
import br.com.on.fiap.core.application.dto.resposta.ClienteResposta;

public interface ClienteController {
    ClienteResposta buscaClientePorCpf(String cpf);

    ClienteResposta insereCliente(ClienteEntrada cliente);
}
