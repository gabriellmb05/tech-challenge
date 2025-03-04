package br.com.on.fiap.hexagono.adapter.controller.base;

import br.com.on.fiap.hexagono.adapter.dto.ClienteEntradaDTO;
import br.com.on.fiap.hexagono.adapter.dto.ClienteRespostaDTO;

public interface ClienteController {
    ClienteRespostaDTO buscaClientePorCpf(String cpf);

    ClienteRespostaDTO insereCliente(ClienteEntradaDTO cliente);
}
