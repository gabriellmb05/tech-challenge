package br.com.on.fiap.core.adapter.controller;

import br.com.on.fiap.core.application.dto.entrada.cliente.ClienteEntrada;
import br.com.on.fiap.core.application.dto.resposta.cliente.ClienteResposta;

public interface ClienteController {
    ClienteResposta buscaClientePorCpf(String cpf);

    ClienteResposta insereCliente(ClienteEntrada cliente);
}
